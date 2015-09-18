package pl.lodz.p.it.spjava.model;

import javax.persistence.*;

@Entity
@Table(name = "USERS", schema = "APP")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "USERNAME", unique = true, nullable = false)
    private String username;

    @Column(name = "PASSWORD", unique = true, nullable = false)
    private String password;

    @Column(name = "IS_ADMIN", nullable = true)
    private Boolean admin;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
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

    public Boolean getAdmin() {
        return admin == null ? false : admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        StringBuffer strBuff = new StringBuffer();
        strBuff.append("id : ").append(getId());
        strBuff.append(", username : ").append(getUsername());
        strBuff.append(", password : ").append(getPassword());
        return strBuff.toString();
    }
}
