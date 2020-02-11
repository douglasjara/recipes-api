package models;

import io.ebean.Model;
import io.ebean.annotation.*;
import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass
public class baseModel extends Model {
    @Id
    private Long id;
    @Version
    private Long version;
    @CreatedTimestamp
    private Timestamp whenCreated;
    @UpdatedTimestamp
    private Timestamp whenUpdated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Timestamp getWhenCreated() {
        return whenCreated;
    }

    public void setWhenCreated(Timestamp whenCreated) {
        this.whenCreated = whenCreated;
    }

    public Timestamp getWhenUpdated() {
        return whenUpdated;
    }

    public void setWhenUpdated(Timestamp whenUpdated) {
        this.whenUpdated = whenUpdated;
    }


}
