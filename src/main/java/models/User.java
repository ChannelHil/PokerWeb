package models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by Channel on 2015-01-16.
 */
@Entity
public class User {

    @Id
    @Size(max=12)
    public String username;
    public String password;
    public byte[] salt;

    @OneToMany(fetch = FetchType.LAZY)
    private  List<GameHistory> gameHistory;

    public User() {
    }

    public User(String username, String password, byte[] salt) {
        this.username = username;
        this.password = password;
        this.salt = salt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }
}
