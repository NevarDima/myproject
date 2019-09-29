package by.nevar.dima.myproject.service.impl;

import by.nevar.dima.myproject.dao.UserDao;
import by.nevar.dima.myproject.dao.impl.DefaultUserDao;
import by.nevar.dima.myproject.model.AuthUser;
import by.nevar.dima.myproject.model.User;
import by.nevar.dima.myproject.service.UserService;

import java.util.List;

public class DefaultUserService implements UserService {
    private UserDao userDao = DefaultUserDao.getInstance();

    private static volatile UserService instance;

    public static UserService getInstance() {
        UserService localInstance = instance;
        if (localInstance == null) {
            synchronized (UserService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultUserService();
                }
            }
        }
        return localInstance;
    }

    @Override
    public List<User> getUser() {
        return userDao.getStudents();
    }

    @Override
    public String saveUser(User user) {
        return userDao.save(user);
    }

    @Override
    public void saveAuthUser(AuthUser authUser) {

    }
}
