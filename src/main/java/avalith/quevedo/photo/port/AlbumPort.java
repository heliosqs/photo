package avalith.quevedo.photo.port;

import avalith.quevedo.photo.domain.Album;

import java.util.List;

public interface AlbumPort {
    boolean existsByAlbumId(int albumId) throws Exception;
}
