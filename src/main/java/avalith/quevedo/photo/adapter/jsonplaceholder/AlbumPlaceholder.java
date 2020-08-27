package avalith.quevedo.photo.adapter.jsonplaceholder;

import avalith.quevedo.photo.domain.Album;
import avalith.quevedo.photo.domain.Photo;
import avalith.quevedo.photo.domain.User;
import avalith.quevedo.photo.port.AlbumPort;
import avalith.quevedo.photo.port.PhotoPort;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class AlbumPlaceholder implements AlbumPort {
    private final WebClient webClient;
    private Logger logger = LoggerFactory.getLogger(AlbumPlaceholder.class);

    public AlbumPlaceholder() {
        this.webClient = WebClient
                .builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Override
    public boolean existsByAlbumId(int albumId) throws Exception {
        final AtomicReference<List<Album>> albums = new AtomicReference<>();
        final AtomicReference<Boolean> isSuccessful = new AtomicReference<>(true);
        Flux<Album> albumsFlux = this.webClient.get().uri(uriBuilder -> uriBuilder
                .path("/albums/{albumId}")
                .build(albumId))
                .retrieve()
                .bodyToFlux(Album.class).publishOn(Schedulers.elastic());
        Thread process = new Thread(() -> albums.set(albumsFlux.subscribeOn(Schedulers.elastic())
                .doOnError(throwable -> {
                    logger.debug(throwable.getMessage());
                    isSuccessful.set(false);
                })
                .collectList().block()));
        process.start();
        try {
            process.join();
            if (isSuccessful.get()) return true;
            throw new Exception("DataAccessError");
        } catch (InterruptedException e) {
            throw new Exception("DataAccessError");
        }
    }

}
