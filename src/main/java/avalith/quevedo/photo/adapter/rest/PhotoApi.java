package avalith.quevedo.photo.adapter.rest;

import avalith.quevedo.photo.domain.Photo;
import avalith.quevedo.photo.domain.ServerResponse;
import avalith.quevedo.photo.service.PhotoSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/photos")
public class PhotoApi {
    @Autowired
    private PhotoSrv photoSrv;

    @GetMapping("")
    ServerResponse<List<Photo>> allUsers(){
        return photoSrv.loadAll();
    }
}
