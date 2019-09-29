package by.nevar.dima.myproject.dao;

import by.nevar.dima.myproject.model.AuthUser;

public interface AuthUserDao {
    AuthUser getByLogin(String login);

    void saveAuthUser(AuthUser user);
}
