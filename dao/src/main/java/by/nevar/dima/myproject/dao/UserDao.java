package by.nevar.dima.myproject.dao;

import by.nevar.dima.myproject.model.User;

import java.util.List;

public interface UserDao {
    List<User> getUsers();

    String save(User user);
}
