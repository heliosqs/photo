package avalith.quevedo.photo.adapter.rest;

import avalith.quevedo.photo.adapter.jsonplaceholder.AlbumPlaceholder;
import avalith.quevedo.photo.adapter.jsonplaceholder.UserPlaceholder;
import avalith.quevedo.photo.adapter.repository.AlbumAuthorizationRep;
import avalith.quevedo.photo.domain.AlbumAuthorization;
import avalith.quevedo.photo.domain.ServerResponse;
import avalith.quevedo.photo.service.AlbumAuthorizationSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albumauth")
public class AlbumAuthorizationApi {
    @Autowired
    private AlbumAuthorizationSrv albumAuthSrv = new AlbumAuthorizationSrv(new UserPlaceholder(), new AlbumAuthorizationRep(), new AlbumPlaceholder());

    @GetMapping(value = "/{albumId}")
    public ServerResponse<AlbumAuthorization> getAlbumAuthorization(@PathVariable(name = "albumId") Integer albumId){
        return albumAuthSrv.listAuthorizedUsers(albumId);
    }

    @PostMapping(value = "")
    public ServerResponse<String> getAlbumAuthorization(@RequestParam(name = "albumid") Integer albumId, @RequestParam(name = "userid") Integer userId){
        return albumAuthSrv.addAlbumAuthorization(albumId, userId);
    }
}
