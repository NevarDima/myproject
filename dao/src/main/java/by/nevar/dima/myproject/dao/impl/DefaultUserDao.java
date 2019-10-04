package by.nevar.dima.myproject.dao.impl;

import by.nevar.dima.myproject.dao.UserDao;
import by.nevar.dima.myproject.dao.jdbc.MysqlDataBase;
import by.nevar.dima.myproject.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DefaultUserDao implements UserDao {
    private static volatile UserDao instance;

    public DefaultUserDao() {
        this.users = new ArrayList<>();
    }

    public static UserDao getInstance() {
        UserDao localInstance = instance;
        if (localInstance == null) {
            synchronized (UserDao.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultUserDao();
                }
            }
        }
        return localInstance;
    }

    private List<User> users;

    @Override
    public List<User> getUsers() {
        return users;
    }

//    @Override
//    public String save(User user) {
//        users.add(user);
//        return user.getId();
//    }
    @Override
    public String save(User user) throws SQLException {
        MysqlDataBase dataBase = new MysqlDataBase();
        try (Connection connection = dataBase.connect();
             PreparedStatement statement = connection.prepareStatement("insert into user (name, surname, email, phone) values(?,?,?,?)")) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhone());
            statement.executeUpdate();
        }
        return user.getId();
    }
}
