package avalith.quevedo.photo.domain;

import java.util.ArrayList;
import java.util.List;

public class AlbumAuthorization {
    private int album;
    private List<User> authUsers;

    public AlbumAuthorization(){
        this.album = 0;
        this.authUsers = new ArrayList<>();
    }

    public AlbumAuthorization(int album, List<User> authUsers) {
        this.album = album;
        this.authUsers = authUsers;
    }

    public int getAlbum() {
        return album;
    }

    public void setAlbum(int album) {
        this.album = album;
    }

    public List<User> getAuthUsers() {
        return authUsers;
    }

    public void setAuthUsers(List<User> authUsers) {
        this.authUsers = authUsers;
    }
}
