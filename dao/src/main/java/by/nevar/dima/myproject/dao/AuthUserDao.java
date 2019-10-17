package by.nevar.dima.myproject.dao;

import by.nevar.dima.myproject.model.AuthUser;

public interface AuthUserDao {
    AuthUser getByLogin(String login);

    AuthUser getById(long id);

    AuthUser saveAuthUser(AuthUser user);

    boolean updateAuthUser(AuthUser user);

    boolean deleteAuthUser(long id);
}
