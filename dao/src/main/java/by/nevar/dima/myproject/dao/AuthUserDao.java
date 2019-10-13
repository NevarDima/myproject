package by.nevar.dima.myproject.dao;

import by.nevar.dima.myproject.model.AuthUser;

public interface AuthUserDao {
    AuthUser getByLogin(String login);

    long saveAuthUser(AuthUser user);

    boolean update(AuthUser user);

    boolean delete(AuthUser user);
}
