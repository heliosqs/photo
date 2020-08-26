package avalith.quevedo.photo.adapter.repository.repinterface;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "albumauth")
public class AlbumAuthDB {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "albumid")
    private Integer albumid;
    @Column(name = "userid")
    private Integer userid;

    public AlbumAuthDB(){
        this.id = 0;
        this.albumid = 0;
        this.userid = 0;
    }

    public AlbumAuthDB(Integer id, Integer albumid, Integer userid) {
        this.id = id;
        this.albumid = albumid;
        this.userid = userid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAlbumid() {
        return albumid;
    }

    public void setAlbumid(Integer albumid) {
        this.albumid = albumid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}