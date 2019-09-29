package by.nevar.dima.myproject.dao.impl;

import by.nevar.dima.myproject.dao.AuthUserDao;
import by.nevar.dima.myproject.model.AuthUser;
import by.nevar.dima.myproject.model.Role;

import java.util.HashMap;
import java.util.Map;

public class DefaultAuthUserDao implements AuthUserDao {
    Map<String, AuthUser> userByLogin;

    public DefaultAuthUserDao() {
        this.userByLogin = new HashMap<>();
        this.userByLogin.putIfAbsent("admin",
                new AuthUser("admin", "admin", Role.ADMINISTRATOR, null));
        this.userByLogin.putIfAbsent("user",
                new AuthUser("user", "user", Role.USER, null));
    }

    private static volatile AuthUserDao instance;

    public static AuthUserDao getInstance() {
        AuthUserDao localInstance = instance;
        if (localInstance == null) {
            synchronized (AuthUserDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultAuthUserDao();
                }
            }
        }
        return localInstance;
    }

    @Override
    public AuthUser getByLogin(String login) {
        return userByLogin.get(login);
    }

    @Override
    public void saveAuthUser(AuthUser user) {
        userByLogin.putIfAbsent(user.getLogin(), user);
    }
}
