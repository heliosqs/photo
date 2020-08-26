package avalith.quevedo.photo.port;

import avalith.quevedo.photo.domain.AlbumAuthorization;

import java.util.List;

public interface AlbumAuthPort {
    AlbumAuthorization getAuthUsersByAlbum(int albumId);
    boolean authUserToAlbum(int albumId, int userId);
}
