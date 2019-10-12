package by.nevar.dima.myproject.dao.impl;

import by.nevar.dima.myproject.dao.AuthUserDao;
import by.nevar.dima.myproject.dao.DataSource;
import by.nevar.dima.myproject.model.AuthUser;
import by.nevar.dima.myproject.model.Role;

import java.sql.*;


public class DefaultAuthUserDao implements AuthUserDao {

    private static class SingletonHolder {
        static final AuthUserDao HOLDER_INSTANCE = new DefaultAuthUserDao();
    }

    public static AuthUserDao getInstance() {
        return DefaultAuthUserDao.SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public AuthUser getByLogin(String login) {
        try(Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from auth_user where login = ?")) {
                statement.setString(1,login);
                try(ResultSet resultSet = statement.executeQuery()){
                    if(resultSet.next()) {
                        return new AuthUser(
                                resultSet.getLong("id"),
                                resultSet.getString("login"),
                                resultSet.getString("password"),
                                Role.valueOf(resultSet.getString("role")),
                                resultSet.getLong("user_id"));

                    } else {
                        return null;
                    }
                }
        } catch (SQLException e){
            throw new RuntimeException();
        }
    }

    @Override
    public long saveAuthUser(AuthUser user) {
        final String sql = "insert into auth_user(login, password, role, user_id) values(?,?,?,?)";
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole().name());
            ps.setLong(4, user.getUserId());
            ps.executeUpdate();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                return generatedKeys.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
