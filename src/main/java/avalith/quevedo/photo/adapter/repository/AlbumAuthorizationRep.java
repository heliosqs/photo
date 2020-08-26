package avalith.quevedo.photo.adapter.repository;

import avalith.quevedo.photo.adapter.repository.repinterface.AlbumAuthDB;
import avalith.quevedo.photo.domain.AlbumAuthorization;
import avalith.quevedo.photo.domain.User;
import avalith.quevedo.photo.port.AlbumAuthPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumAuthorizationRep implements AlbumAuthPort{
    @Autowired
    private AlbumAuthorizationI albumAuthCrud;

    @Override
    public AlbumAuthorization getAuthUsersByAlbum(int albumId) {
        List<AlbumAuthDB> albumAuthList = new ArrayList<AlbumAuthDB>();
        albumAuthCrud.findAllByAlbumid(albumId).iterator().forEachRemaining(albumAuthList::add);
        AlbumAuthorization albumauth = new AlbumAuthorization();
        albumauth.setAlbum(albumId);
        return albumauth;
    }

    @Override
    public boolean authUserToAlbum(int albumId, int userId) {
        return false;
    }


}
