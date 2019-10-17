package by.nevar.dima.myproject.dao;

import by.nevar.dima.myproject.model.Car;
import by.nevar.dima.myproject.model.RoleCar;

import java.util.List;

public interface CarDao {

    Car saveCar(Car car);

    boolean updateCar(Car car);

    boolean deleteCar(long id);

    List<Car> getAllCars();

    List<Car> getByBrand(String brand);

    List<Car> getByModel(String model);

    List<Car> getByRoleCar(RoleCar role);
}
