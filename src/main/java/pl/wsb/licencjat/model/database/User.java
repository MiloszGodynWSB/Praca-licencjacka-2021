package pl.wsb.licencjat.model.database;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "ID")
    private long id;
    private String username;
    private String password;
    private int enabled;

    public long getId() {
        return id;
    }
}
