package by.nevar.dima.myproject.dao.impl;

import by.nevar.dima.myproject.dao.AuthUserDao;
import by.nevar.dima.myproject.dao.DataSource;
import by.nevar.dima.myproject.model.AuthUser;
import by.nevar.dima.myproject.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDateTime;


public class DefaultAuthUserDao implements AuthUserDao {

    private static final Logger log = LoggerFactory.getLogger(DefaultAuthUserDao.class);

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
            log.error("SQLException at: {}", LocalDateTime.now(), e);
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
            log.info("AuthUser id: {} saved to DataBase at: {}", user.getLogin(), LocalDateTime.now());
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                return generatedKeys.getLong(1);
            }
        } catch (SQLException e) {
            log.error("SQLException at: {}", LocalDateTime.now(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(AuthUser user) {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement
                     ("update auth_user set login = ?, password = ?, role = ? where user_id = ?")) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole().toString());
            statement.setLong(4, user.getId());
            log.info("AuthUser id: {} updated in DB at: {}", user.getId(), LocalDateTime.now());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error("SQLException at: {}", LocalDateTime.now(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(AuthUser user) {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statementAuthUser = connection.prepareStatement
                     ("delete from auth_user where user_id = ?");
             PreparedStatement statementUser = connection.prepareStatement
                     ("delete from user where id = ?")) {
            statementAuthUser.setLong(1, user.getId());
            statementUser.setLong(1,user.getId());
            int resStateAuthUser = statementAuthUser.executeUpdate();
            int resStateUser = statementUser.executeUpdate();
            log.info("AuthUser id: {} deleted from DB at: {}", user.getId(), LocalDateTime.now());
            return resStateAuthUser > 0 && resStateUser > 0;
        } catch (SQLException e) {
            log.error("SQLException at: {}", LocalDateTime.now(), e);
            throw new RuntimeException(e);
        }
    }
}
