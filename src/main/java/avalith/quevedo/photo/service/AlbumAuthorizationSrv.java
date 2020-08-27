package avalith.quevedo.photo.service;

import avalith.quevedo.photo.domain.*;
import avalith.quevedo.photo.port.AlbumAuthPort;
import avalith.quevedo.photo.port.AlbumPort;
import avalith.quevedo.photo.port.UserPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumAuthorizationSrv {
    private UserPort user;
    private AlbumPort album;
    private AlbumAuthPort albumAuthorization;

    @Autowired
    public AlbumAuthorizationSrv(UserPort user, AlbumAuthPort albumAuthorization, AlbumPort album) {
        this.user = user;
        this.albumAuthorization = albumAuthorization;
        this.album = album;
    }

    /***
     * Authorize the access of a user to an existing album
     * @param albumId Identifier for the album of photos
     * @param userId Identifier for the user to which we want to give access to the album
     * @return True in case that the registration of the authorization was successful
     */
    public ServerResponse<String> addAlbumAuthorization(int albumId, int userId) {
        ServerResponse<String> response = new ServerResponse<>();
        try {
            if (album.existsByAlbumId(albumId) && user.existsByUserId(userId) && !albumAuthorization.existsByAlbumAndUser(albumId, userId)) {
                albumAuthorization.authUserToAlbum(albumId, userId);
                response.setSuccessful(true);
                response.setError(null);
                response.setObject("Added Successfully");
            } else {
                response.setSuccessful(false);
                response.setError(new ServerError("RequestError", "Error on the request parameters"));
                response.setObject(null);
            }
        } catch (Exception e) {
            response.setSuccessful(false);
            response.setError(new ServerError("DataAccessError", "Error while retrieving the data, please try again"));
            response.setObject(null);
        }
        return response;
    }

    public ServerResponse<AlbumAuthorization> listAuthorizedUsers(int albumId) {
        ServerResponse<AlbumAuthorization> response = new ServerResponse<>();
        try {
            if (album.existsByAlbumId(albumId)) {
                List<Integer> userIds = albumAuthorization.getAuthUserIdsByAlbum(albumId);
                List<User> users = user.loadById(userIds);
                AlbumAuthorization albumAuthorization = new AlbumAuthorization();
                albumAuthorization.setAlbum(albumId);
                albumAuthorization.setAuthUsers(users);
                response.setSuccessful(true);
                response.setError(null);
                response.setObject(albumAuthorization);
            }else{
                response.setSuccessful(false);
                response.setError(new ServerError("RequestError", "Error in one of the request's parameters"));
                response.setObject(null);
            }
        }catch(Exception e){
            response.setSuccessful(false);
            response.setError(new ServerError("DataAccessError", "Error while retrieving the data, please try again"));
            response.setObject(null);
        }
        return response;
    }
}