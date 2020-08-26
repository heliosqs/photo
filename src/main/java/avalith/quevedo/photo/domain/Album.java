package avalith.quevedo.photo.domain;

import java.io.Serializable;

public class Album implements Serializable {
    private int id;
    private int userId;
    private String title;

    public Album(){
        this.title = "";
    }

    public Album(int id, int userId, String title) {
        this.id = id;
        this.userId = userId;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
