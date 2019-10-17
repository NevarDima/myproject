package by.nevar.dima.myproject.service;

import by.nevar.dima.myproject.model.AuthUser;
import by.nevar.dima.myproject.model.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

    Long saveUser(User user);

    boolean updateUser(User user);
}

