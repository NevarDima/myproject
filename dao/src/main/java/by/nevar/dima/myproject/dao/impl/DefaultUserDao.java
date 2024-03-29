package by.nevar.dima.myproject.dao.impl;

import by.nevar.dima.myproject.dao.DataSource;
import by.nevar.dima.myproject.dao.HibernateUtil;
import by.nevar.dima.myproject.dao.UserDao;
import by.nevar.dima.myproject.dao.converter.UserConverter;
import by.nevar.dima.myproject.dao.entity.UserEntity;
import by.nevar.dima.myproject.model.User;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultUserDao implements UserDao {

    private static final Logger log = LoggerFactory.getLogger(DefaultAuthUserDao.class);

    private static class SingletonHolder {
        static final UserDao HOLDER_INSTANCE = new DefaultUserDao();
    }

    public static UserDao getInstance() {
        return DefaultUserDao.SingletonHolder.HOLDER_INSTANCE;
    }


    @Override
    public List<User> getUsers() {
/* JDBC realisation
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from user");
             ResultSet resultSet = statement.executeQuery()) {
            final ArrayList<User> result = new ArrayList<>();
            while (resultSet.next()) {
                final User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getLong("auth_id"));
                result.add(user);
            }
            return result;
        } catch (SQLException e) {
            log.error("SQLException : {}", LocalDateTime.now(), e);
            throw new RuntimeException(e);
        }
 */

        final List<UserEntity> authUser = HibernateUtil.getSession().createQuery("from UserEntity")
                .list();
        return authUser.stream()
                .map(UserConverter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public long saveUser(User user) {
/* JDBC realisation
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
            log.error("SQLException : {}", LocalDateTime.now(), e);
            throw new RuntimeException(e);
        }
 */

        UserEntity userEntity = UserConverter.toEntity(user);
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(userEntity);
        session.getTransaction().commit();
        return userEntity.getId();
    }

    @Override
    public boolean updateUser(User user) {
/* JDBC realisation
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement
                     ("update user set first_name = ?, last_name = ?, email = ?, phone = ? where auth_id = ?")) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhone());
            log.info("User auth_id - {} updated : {}", user.getAuthId(), LocalDateTime.now());
            return statement.executeUpdate()>0;
        } catch (SQLException e) {
            log.error("SQLException : {}", LocalDateTime.now(), e);
            throw new RuntimeException(e);
        }
 */

        UserEntity userEntity = UserConverter.toEntity(user);
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.update(userEntity);
        session.getTransaction().commit();
        return true; //TODO change return type
    }

}
