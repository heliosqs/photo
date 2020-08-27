package avalith.quevedo.photo.adapter.jsonplaceholder;

import avalith.quevedo.photo.domain.ServerError;
import avalith.quevedo.photo.domain.User;
import avalith.quevedo.photo.port.UserPort;
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
public class UserPlaceholder implements UserPort {
    private final WebClient webClient;
    private Logger logger = LoggerFactory.getLogger(UserPlaceholder.class);

    public UserPlaceholder(){
        this.webClient = WebClient
                .builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    @Override
    public List<User> loadAll() throws Exception {
        final AtomicReference<List<User>> users = new AtomicReference<>();
        final AtomicReference<Boolean> isSuccessful = new AtomicReference<>(true);
        Flux<User> usersFlux = this.webClient.get().uri("/users").retrieve().bodyToFlux(User.class)
                .publishOn(Schedulers.elastic());
        Thread process = new Thread(() -> users.set(usersFlux.subscribeOn(Schedulers.elastic())
                .doOnError(throwable -> {
                    logger.debug(throwable.getMessage());
                    isSuccessful.set(false);
                }).collectList().block()));
        process.start();
        try {
            process.join();
            if (isSuccessful.get()) return users.get();
            throw new Exception("DataAccessError");
        } catch (InterruptedException e) {
            throw new Exception("DataAccessError");
        }
    }

    @Override
    public List<User> loadById(List<Integer> userIds) throws Exception {
        final AtomicReference<List<User>> users = new AtomicReference<>();
        final AtomicReference<Boolean> isSuccessful = new AtomicReference<>(true);
        Flux<User> usersFlux = this.webClient.get().uri("/users").retrieve().bodyToFlux(User.class).publishOn(Schedulers.elastic());
        Thread process = new Thread(() -> users.set(usersFlux.subscribeOn(Schedulers.elastic())
                .doOnError(throwable -> {
                    logger.debug(throwable.getMessage());
                    isSuccessful.set(false);
                }).filter(user -> userIds.contains(user.getId())).collectList().block()));
        process.start();
        try {
            process.join();
            if (isSuccessful.get()) return users.get();
            throw new Exception("DataAccessError");
        } catch (InterruptedException e) {
            throw new Exception("DataAccessError");
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
