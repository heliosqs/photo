package avalith.quevedo.photo.adapter.repository;

import avalith.quevedo.photo.adapter.repository.repinterface.AlbumAuthDB;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumAuthorizationI extends CrudRepository<AlbumAuthDB, Long> {
    List<AlbumAuthDB> findAllByAlbumid(int albumid);
    boolean existsByAlbumidAndUserid(int albumid, int userid);
}
