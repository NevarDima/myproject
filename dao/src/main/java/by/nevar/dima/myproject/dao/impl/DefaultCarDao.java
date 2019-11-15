package by.nevar.dima.myproject.dao.impl;

import by.nevar.dima.myproject.dao.CarDao;
import by.nevar.dima.myproject.dao.DataSource;
import by.nevar.dima.myproject.dao.HibernateUtil;
import by.nevar.dima.myproject.dao.converter.AuthUserConverter;
import by.nevar.dima.myproject.dao.converter.CarConverter;
import by.nevar.dima.myproject.dao.entity.CarEntity;
import by.nevar.dima.myproject.model.Car;
import by.nevar.dima.myproject.model.RoleCar;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultCarDao implements CarDao {

    private static final Logger log = LoggerFactory.getLogger(DefaultAuthUserDao.class);

    private static class SingletonHolder {
        static final CarDao HOLDER_INSTANCE = new DefaultCarDao();
    }

    public static CarDao getInstance() {
        return DefaultCarDao.SingletonHolder.HOLDER_INSTANCE;
    }

    @Override
    public Car saveCar(Car car) {
/* JDBC realisation
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement
                     ("insert into car(brand, model, role_car) values(?,?,?)")) {
            statement.setString(1, car.getBrand());
            statement.setString(2, car.getModel());
            statement.setString(3, car.getRoleCar().toString());
            statement.executeUpdate();
            long id;
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if(generatedKeys.next()){
                    id = generatedKeys.getLong(1);
                }else{
                    return null;
                }
            }
            log.info("Car id - {} saved : {}", id, LocalDateTime.now());
            return new Car(car.getBrand(), car.getModel(), car.getRoleCar());
        } catch (SQLException e) {
            log.error("SQLException : {}", LocalDateTime.now(), e);
            throw new RuntimeException(e);
        }
 */

        CarEntity carEntity = CarConverter.toEntity(car);
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(carEntity);
        session.getTransaction().commit();
        return CarConverter.fromEntity(carEntity);
    }

    @Override
    public boolean updateCar(Car car) {
/* JDBC realisation
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement
                     ("update car set brand = ?, model = ?, role_car = ? where id = ?")) {
            statement.setString(1, car.getBrand());
            statement.setString(2, car.getModel());
            statement.setString(3, car.getRoleCar().toString());
            statement.setLong(4,car.getId());
            log.info("Car id - {} updated : {}", car.getId(), LocalDateTime.now());
            return statement.executeUpdate()>0;
        } catch (SQLException e) {
            log.error("SQLException : {}", LocalDateTime.now(), e);
            throw new RuntimeException(e);
        }
 */

        CarEntity carEntity = CarConverter.toEntity(car);
        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.update(carEntity);
        session.getTransaction().commit();
        return true; //TODO change return type
    }

    @Override
    public boolean deleteCar(long id) {
/* JDBC realisation
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement
                     ("delete from car where id = ?")) {
            statement.setLong(1,id);
            log.info("Car id - {} deleted : {}", id, LocalDateTime.now());
            return statement.executeUpdate()>0;
        } catch (SQLException e) {
            log.error("SQLException : {}", LocalDateTime.now(), e);
            throw new RuntimeException(e);
        }
 */

        final Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.createQuery("delete CarEntity where id = :id").executeUpdate();
        session.getTransaction().commit();

        return true; //TODO change return type
    }

    @Override
    public List<Car> getAllCars(){
/* JDBC realisation
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from car order by id desc limit 10");
             ResultSet resultSet = statement.executeQuery()) {
            return getList(resultSet);
        } catch (SQLException e) {
            log.error("SQLException : {}", LocalDateTime.now(), e);
            throw new RuntimeException(e);
        }
 */

        final List<CarEntity> car = HibernateUtil.getSession().createQuery("from CarEntity")
                .list();
        return car.stream()
                .map(CarConverter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> getByBrand(String brand) {
/* JDBC realisation
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from car where brand = ? order by id desc limit 10");
             ResultSet resultSet = statement.executeQuery()) {
            statement.setString(1, brand);
            return getList(resultSet);
        } catch (SQLException e) {
            log.error("SQLException : {}", LocalDateTime.now(), e);
            throw new RuntimeException(e);
        }
 */

        final List<CarEntity> car = HibernateUtil.getSession().createQuery("from CarEntity where brand = :brand")
                .list();
        return car.stream()
                .map(CarConverter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> getByModel(String model) {
/* JDBC realisation
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from car where model = ? order by id desc limit 10");
             ResultSet resultSet = statement.executeQuery()) {
            statement.setString(1, model);
            return getList(resultSet);
        } catch (SQLException e) {
            log.error("SQLException : {}", LocalDateTime.now(), e);
            throw new RuntimeException(e);
        }
 */

        final List<CarEntity> car = HibernateUtil.getSession().createQuery("from CarEntity where model = :model")
                .list();
        return car.stream()
                .map(CarConverter::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Car> getByRoleCar(RoleCar role) {
/* JDBC realisation
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from car where role_car = ? order by id desc limit 10");
             ResultSet resultSet = statement.executeQuery()) {
            statement.setString(1, role.toString());
            return getList(resultSet);
        } catch (SQLException e) {
            log.error("SQLException : {}", LocalDateTime.now(), e);
            throw new RuntimeException(e);
        }
 */

        final List<CarEntity> car = HibernateUtil.getSession().createQuery("from CarEntity where roleCar = :role")
                .list();
        return car.stream()
                .map(CarConverter::fromEntity)
                .collect(Collectors.toList());
    }
/* JDBC realisation
    private List<Car> getList(ResultSet resultSet) throws SQLException {
        final ArrayList<Car> result = new ArrayList<>();
        while (resultSet.next()) {
            final Car car = new Car(
                    resultSet.getLong("id"),
                    resultSet.getString("brand"),
                    resultSet.getString("model"),
                    RoleCar.valueOf(resultSet.getString("role_car")),
                    resultSet.getBoolean("blocked"));
            result.add(car);
        }
        return result;
    }
 */

}
