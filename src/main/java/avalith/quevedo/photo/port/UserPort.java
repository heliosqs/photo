package avalith.quevedo.photo.port;

import avalith.quevedo.photo.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserPort {
    List<User> loadAll();
    List<User> loadById(List<Integer> userIds);
    boolean existsByUserId(int userId);
}
