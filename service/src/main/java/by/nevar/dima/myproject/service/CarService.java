package by.nevar.dima.myproject.service;

import by.nevar.dima.myproject.model.Car;

import java.util.List;

public interface CarService {

    Car saveCar(Car car);

    boolean updateCar(Car car);

    boolean deleteCar(String id);

    List<Car> getAllCars();

    List<Car> getByBrand(String brand);

    List<Car> getByModel(String model);

    List<Car> getByRoleCar(String role);
}
