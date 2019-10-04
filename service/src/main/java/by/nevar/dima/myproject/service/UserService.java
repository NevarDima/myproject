package by.nevar.dima.myproject.service;

import by.nevar.dima.myproject.model.AuthUser;
import by.nevar.dima.myproject.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    List<User> getUser();

    String saveUser(User user) throws SQLException;

    void saveAuthUser(AuthUser authUser);
}

