package avalith.quevedo.photo.adapter.rest;

import avalith.quevedo.photo.adapter.jsonplaceholder.UserPlaceholder;
import avalith.quevedo.photo.domain.User;
import avalith.quevedo.photo.port.UserPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserApi {
    private UserPort userPort = new UserPlaceholder();

    @GetMapping("")
    Iterable<User> allUsers(){
        userPort.existsByUserId(25);
        return userPort.loadAll();
    }
}
