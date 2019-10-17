package by.nevar.dima.myproject.service.impl;

import by.nevar.dima.myproject.dao.UserDao;
import by.nevar.dima.myproject.dao.impl.DefaultUserDao;
import by.nevar.dima.myproject.model.AuthUser;
import by.nevar.dima.myproject.model.User;
import by.nevar.dima.myproject.service.UserService;

import java.util.List;

public class DefaultUserService implements UserService {

    private static class SingletonHolder {
        static final UserService HOLDER_INSTANCE = new DefaultUserService();
    }

    public static UserService getInstance() {
        return DefaultUserService.SingletonHolder.HOLDER_INSTANCE;
    }

    private UserDao userDao = DefaultUserDao.getInstance();

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public Long saveUser(User user) {
        return userDao.saveUser(user);
    }

    @Override
    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }
}
