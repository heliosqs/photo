package avalith.quevedo.photo.adapter.rest;

import avalith.quevedo.photo.adapter.jsonplaceholder.PhotoPlaceholder;
import avalith.quevedo.photo.adapter.repository.AlbumAuthorizationRep;
import avalith.quevedo.photo.domain.AlbumAuthorization;
import avalith.quevedo.photo.port.PhotoPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/albumauth")
public class AlbumAuthorizationApi {
    @Autowired
    private AlbumAuthorizationRep authRep;

    @GetMapping(value = "/{albumId}")
    public AlbumAuthorization getAlbumAuthorization(@PathVariable(name = "albumId") Integer albumId){
        return authRep.getAuthUsersByAlbum(albumId);
    }
}
