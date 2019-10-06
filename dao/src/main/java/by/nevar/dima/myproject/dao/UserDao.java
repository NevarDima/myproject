package by.nevar.dima.myproject.dao;

import by.nevar.dima.myproject.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    List<User> getUsers() throws SQLException;

    String save(User user) throws SQLException;
}
