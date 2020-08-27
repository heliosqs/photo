package avalith.quevedo.photo.port;

import avalith.quevedo.photo.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserPort {
    List<User> loadAll() throws Exception;
    List<User> loadById(List<Integer> userIds) throws Exception;
    boolean existsByUserId(int userId);
}
