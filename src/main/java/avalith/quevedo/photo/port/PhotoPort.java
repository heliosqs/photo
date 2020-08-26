package avalith.quevedo.photo.port;

import avalith.quevedo.photo.domain.Photo;

import java.util.List;

public interface PhotoPort {
    List<Photo> loadAll();
}
