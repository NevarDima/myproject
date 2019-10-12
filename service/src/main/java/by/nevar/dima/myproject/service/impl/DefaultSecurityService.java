package by.nevar.dima.myproject.service.impl;

import by.nevar.dima.myproject.dao.AuthUserDao;
import by.nevar.dima.myproject.dao.DataSource;
import by.nevar.dima.myproject.dao.impl.DefaultAuthUserDao;
import by.nevar.dima.myproject.model.AuthUser;
import by.nevar.dima.myproject.service.SecurityService;

public class DefaultSecurityService implements SecurityService {

    private static class SingletonHolder {
        static final SecurityService HOLDER_INSTANCE = new DefaultSecurityService();
    }
    public static SecurityService getInstance() {
        return DefaultSecurityService.SingletonHolder.HOLDER_INSTANCE;
    }

    private AuthUserDao authUserDao = DefaultAuthUserDao.getInstance();

    public AuthUser login(String login, String password) {
        AuthUser user = authUserDao.getByLogin(login);
        if (user == null) {
            return null;
        }
        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
