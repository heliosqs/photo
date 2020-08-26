package avalith.quevedo.photo.port;

import avalith.quevedo.photo.domain.Album;

import java.util.List;

public interface AlbumPort {
    List<Album> loadAll();
    boolean existsByAlbumId(int albumId);
}
