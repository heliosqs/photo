package avalith.quevedo.photo.adapter.rest;

import avalith.quevedo.photo.adapter.jsonplaceholder.PhotoPlaceholder;
import avalith.quevedo.photo.adapter.jsonplaceholder.UserPlaceholder;
import avalith.quevedo.photo.domain.Photo;
import avalith.quevedo.photo.domain.User;
import avalith.quevedo.photo.port.PhotoPort;
import avalith.quevedo.photo.port.UserPort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/photos")
public class PhotoApi {
    private PhotoPort photoPort = new PhotoPlaceholder();

    @GetMapping("")
    Iterable<Photo> allUsers(){
        return photoPort.loadAll();
    }
}
