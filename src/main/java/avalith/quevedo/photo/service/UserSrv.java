package avalith.quevedo.photo.service;

import avalith.quevedo.photo.domain.ServerError;
import avalith.quevedo.photo.domain.ServerResponse;
import avalith.quevedo.photo.domain.User;
import avalith.quevedo.photo.port.UserPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSrv {
    private UserPort userPort;

    @Autowired
    public UserSrv(UserPort userPort) {
        this.userPort = userPort;
    }

    public ServerResponse<List<User>> loadAll() {
        List<User> users = null;
        ServerResponse<List<User>> response = new ServerResponse<>();
        try {
            users = userPort.loadAll();
            response.setSuccessful(true);
            response.setError(null);
            response.setObject(users);
        } catch (Exception e) {
            response.setSuccessful(false);
            response.setObject(null);
            response.setError(new ServerError("DataAccessError", "Error while retrieving the data, please try again"));
        }
        return response;
    }
}
