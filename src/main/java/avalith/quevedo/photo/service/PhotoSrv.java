package avalith.quevedo.photo.service;

import avalith.quevedo.photo.domain.Photo;
import avalith.quevedo.photo.domain.ServerError;
import avalith.quevedo.photo.domain.ServerResponse;
import avalith.quevedo.photo.port.PhotoPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoSrv {
    private PhotoPort photoPort;

    @Autowired
    public PhotoSrv(PhotoPort photoPort) {
        this.photoPort = photoPort;
    }

    public ServerResponse<List<Photo>> loadAll() {
        List<Photo> photos = null;
        ServerResponse<List<Photo>> response = new ServerResponse<>();
        try {
            photos = photoPort.loadAll();
            response.setSuccessful(true);
            response.setError(null);
            response.setObject(photos);
        } catch (Exception e) {
            response.setSuccessful(false);
            response.setObject(null);
            response.setError(new ServerError("DataAccessError", "Error while retrieving the data, please try again"));
        }
        return response;
    }
}
