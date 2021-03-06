package avalith.quevedo.photo.adapter.jsonplaceholder;

import avalith.quevedo.photo.domain.Photo;
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
public class PhotoPlaceholder implements PhotoPort {
    private final WebClient webClient;
    private Logger logger = LoggerFactory.getLogger(PhotoPlaceholder.class);

    public PhotoPlaceholder() {
        this.webClient = WebClient
                .builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Override
    public List<Photo> loadAll() throws Exception {
        final AtomicReference<List<Photo>> photos = new AtomicReference<>();
        final AtomicReference<Boolean> isSuccessful = new AtomicReference<>(true);
        Flux<Photo> photosFlux = this.webClient.get().uri("/photos").retrieve().bodyToFlux(Photo.class).publishOn(Schedulers.elastic());
        Thread process = new Thread(() -> photos.set(photosFlux.subscribeOn(Schedulers.elastic())
                .doOnError(throwable -> {
                    logger.debug(throwable.getMessage());
                    isSuccessful.set(false);
                }).collectList().block()));
        process.start();
        try {
            process.join();
            if (isSuccessful.get()) return photos.get();
            throw new Exception("DataAccessError");
        } catch (InterruptedException e) {
            throw new Exception("DataAccessError");
        }
    }
}
