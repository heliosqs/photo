package avalith.quevedo.photo.adapter.jsonplaceholder;

import avalith.quevedo.photo.domain.Photo;
import avalith.quevedo.photo.domain.User;
import avalith.quevedo.photo.port.PhotoPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class PhotoPlaceholder implements PhotoPort {
    private final WebClient webClient;

    public PhotoPlaceholder(){
        this.webClient = WebClient
                .builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Override
    public List<Photo> loadAll() {
        final AtomicReference<List<Photo>> photos = new AtomicReference<>();
        Flux<Photo> photosFlux = this.webClient.get().uri("/photos").retrieve().bodyToFlux(Photo.class).publishOn(Schedulers.elastic());
        Thread process = new Thread(() -> photos.set(photosFlux.subscribeOn(Schedulers.elastic()).collectList().block()));
        process.start();
        try {
            process.join();
            return photos.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
