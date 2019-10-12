package by.nevar.dima.myproject.service.impl;

import by.nevar.dima.myproject.dao.impl.DefaultUserDao;
import by.nevar.dima.myproject.model.AuthUser;
import by.nevar.dima.myproject.model.User;
import by.nevar.dima.myproject.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class DefaultUserService implements UserService {
    private static class SingletonHolder {
        static final UserService HOLDER_INSTANCE = new DefaultUserService();
    }

    public static UserService getInstance() {
        return DefaultUserService.SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public List<User> getUsers() {
        try {
            return DefaultUserDao.getInstance().getUsers();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Long saveUser(User user) {
        try {
            return DefaultUserDao.getInstance().save(user);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void saveAuthUser(AuthUser authUser) {

    }
}
