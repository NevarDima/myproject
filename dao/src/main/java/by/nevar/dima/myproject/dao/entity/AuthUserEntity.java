package by.nevar.dima.myproject.dao.entity;

import by.nevar.dima.myproject.model.RoleUser;

import javax.persistence.*;

@Entity
@Table(name = "auth_user")
public class AuthUserEntity {

    private Long id;
    private String login;
    private String password;
    private RoleUser roleUser;

    public AuthUserEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "role_user")
    public RoleUser getRole() {
        return roleUser;
    }

    public void setRole(RoleUser roleUser) {
        this.roleUser = roleUser;
    }

}
