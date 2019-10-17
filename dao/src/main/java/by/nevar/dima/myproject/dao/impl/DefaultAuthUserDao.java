package by.nevar.dima.myproject.dao.impl;

import by.nevar.dima.myproject.dao.AuthUserDao;
import by.nevar.dima.myproject.dao.DataSource;
import by.nevar.dima.myproject.model.AuthUser;
import by.nevar.dima.myproject.model.RoleUser;
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
                                RoleUser.valueOf(resultSet.getString("role_user")));

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
    public AuthUser getById(long id) {
        try(Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("select * from auth_user where id = ?")) {
            statement.setLong(1, id);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()) {
                    return new AuthUser(
                            resultSet.getLong("id"),
                            resultSet.getString("login"),
                            resultSet.getString("password"),
                            RoleUser.valueOf(resultSet.getString("role_user")));

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
    public AuthUser saveAuthUser(AuthUser user) {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statementAuthUser = connection.prepareStatement
                     ("insert into auth_user(login, password) values(?,?)", Statement.RETURN_GENERATED_KEYS);
             PreparedStatement statementUser = connection.prepareStatement
                     ("insert into user (auth_id) values (?)")) {
            statementAuthUser.setString(1, user.getLogin());
            statementAuthUser.setString(2, user.getPassword());
            long id;
            try (ResultSet generatedKeys = statementAuthUser.getGeneratedKeys()) {
                if(generatedKeys.next()){
                    id = generatedKeys.getLong(1);
                }else{
                    return null;
                }
            }
            statementUser.setLong(1,id);
            statementUser.executeUpdate();
            log.info("AuthUser id - {} saved : {}", user.getLogin(), LocalDateTime.now());
            return new AuthUser(id, user.getLogin(), user.getPassword(), RoleUser.USER);
        } catch (SQLException e) {
            log.error("SQLException : {}", LocalDateTime.now(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateAuthUser(AuthUser user) {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement
                     ("update auth_user set login = ?, password = ?, role_user = ? where id = ?")) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRoleUser().toString());
            statement.setLong(4, user.getId());
            log.info("AuthUser id - {} updated : {}", user.getId(), LocalDateTime.now());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            log.error("SQLException : {}", LocalDateTime.now(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteAuthUser(long id) {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statementAuthUser = connection.prepareStatement
                     ("delete from auth_user where id = ?");
             PreparedStatement statementUser = connection.prepareStatement
                     ("delete from user where auth_id = ?")) {
            statementAuthUser.setLong(1, id);
            int resStateAuthUser = statementAuthUser.executeUpdate();
            statementUser.setLong(1,id);
            int resStateUser = statementUser.executeUpdate();
            log.info("AuthUser id - {} deleted : {}", id, LocalDateTime.now());
            return resStateAuthUser > 0 && resStateUser > 0;
        }  catch (SQLException e) {
            log.error("SQLException at: {}", LocalDateTime.now(), e);
            throw new RuntimeException(e);
        }
    }
}
