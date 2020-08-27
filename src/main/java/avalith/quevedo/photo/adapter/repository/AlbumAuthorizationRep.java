package avalith.quevedo.photo.adapter.repository;

import avalith.quevedo.photo.adapter.repository.repinterface.AlbumAuthDB;
import avalith.quevedo.photo.domain.AlbumAuthorization;
import avalith.quevedo.photo.port.AlbumAuthPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumAuthorizationRep implements AlbumAuthPort{
    @Autowired
    private AlbumAuthorizationI albumAuthCrud;

    public AlbumAuthorizationRep(){};
    @Override
    public List<Integer> getAuthUserIdsByAlbum(int albumId) {
        List<Integer> authUsers = new ArrayList<Integer>();
        albumAuthCrud.findAllByAlbumid(albumId).iterator().forEachRemaining(albumAuth -> authUsers.add(albumAuth.getUserid()));
        AlbumAuthorization albumauth = new AlbumAuthorization();
        albumauth.setAlbum(albumId);
        return authUsers;
    }

    @Override
    public boolean authUserToAlbum(int albumId, int userId) {
        AlbumAuthDB albumAuth = new AlbumAuthDB(albumId, userId);
        albumAuthCrud.save(albumAuth);
        return true;
    }

    public boolean existsByAlbumAndUser(int albumid, int userid){
        return albumAuthCrud.existsByAlbumidAndUserid(albumid, userid);
    }


}
