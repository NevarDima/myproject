package by.nevar.dima.myproject.dao.impl;

import by.nevar.dima.myproject.dao.DataSource;
import by.nevar.dima.myproject.dao.UserDao;
import by.nevar.dima.myproject.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultUserDao implements UserDao {

    private static class SingletonHolder {
        static final UserDao HOLDER_INSTANCE = new DefaultUserDao();
    }

    public static UserDao getInstance() {
        return DefaultUserDao.SingletonHolder.HOLDER_INSTANCE;
    }


    @Override
    public List<User> getUsers() {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from user");
             ResultSet resultSet = statement.executeQuery()) {
            final ArrayList<User> result = new ArrayList<>();
            while (resultSet.next()) {
                final User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"));
                result.add(user);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long save(User user) {
        final String sql = "insert into user(first_name, last_name, phone, email) values(?,?,?,?)";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getEmail());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                keys.next();
                return keys.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
