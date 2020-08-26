package avalith.quevedo.photo.adapter.jsonplaceholder;

import avalith.quevedo.photo.domain.User;
import avalith.quevedo.photo.port.UserPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class UserPlaceholder implements UserPort {
    private final WebClient webClient;

    public UserPlaceholder(){
        this.webClient = WebClient
                .builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Override
    public List<User> loadAll() {
        final AtomicReference<List<User>> users = new AtomicReference<>();
        Flux<User> usersFlux = this.webClient.get().uri("/users").retrieve().bodyToFlux(User.class).publishOn(Schedulers.elastic());
        Thread process = new Thread(() -> users.set(usersFlux.subscribeOn(Schedulers.elastic()).collectList().block()));
        process.start();
        try {
            process.join();
            return users.get();
        } catch (InterruptedException e) {
            return null;
        }
    }

    @Override
    public List<User> loadById(List<Integer> userIds) {
        final AtomicReference<List<User>> users = new AtomicReference<>();
        Flux<User> usersFlux = this.webClient.get().uri("/users").retrieve().bodyToFlux(User.class).publishOn(Schedulers.elastic());
        Thread process = new Thread(() -> users.set(usersFlux.subscribeOn(Schedulers.elastic()).filter(user -> userIds.contains(user.getId())).collectList().block()));
        process.start();
        try {
            process.join();
            return users.get();
        } catch (InterruptedException e) {
            return null;
        }
    }

    @Override
    public boolean existsByUserId(int userId) {
        final AtomicReference<List<User>> users = new AtomicReference<>();
        Flux<User> usersFlux = this.webClient.get().uri(uriBuilder -> uriBuilder
                .path("/users/{userId}")
                .build(userId))
                .retrieve()
                .bodyToFlux(User.class).publishOn(Schedulers.elastic());
        Thread process = new Thread(() -> users.set(usersFlux.subscribeOn(Schedulers.elastic()).onErrorReturn(new User()).collectList().block()));
        process.start();
        try {
            process.join();
            if(users.get().get(0).getId() == 0) {
                return false;
            }else{
                return true;
            }
        } catch (InterruptedException e) {
            return false;
        }
    }
}
