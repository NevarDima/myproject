package by.nevar.dima.myproject.model;

public class AuthUser {
    private Long id;
    private String login;
    private String password;
    private RoleUser roleUser;

    public AuthUser(){
    }

    public AuthUser(String login, String password){
        this.login = login;
        this.password = password;
        this.roleUser = RoleUser.USER;
    }

    public AuthUser(Long id, String login, String password, RoleUser roleUser){
        this.id = id;
        this.login = login;
        this.password = password;
        this.roleUser = roleUser;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public RoleUser getRoleUser() {
        return roleUser;
    }
}
