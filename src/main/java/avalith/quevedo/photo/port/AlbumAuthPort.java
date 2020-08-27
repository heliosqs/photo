package avalith.quevedo.photo.port;

import avalith.quevedo.photo.domain.AlbumAuthorization;

import java.util.List;

public interface AlbumAuthPort {
    boolean existsByAlbumAndUser(int albumid, int userid);
    List<Integer> getAuthUserIdsByAlbum(int albumId);
    boolean authUserToAlbum(int albumId, int userId);
}
