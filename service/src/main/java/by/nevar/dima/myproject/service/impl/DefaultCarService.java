package by.nevar.dima.myproject.service.impl;

import by.nevar.dima.myproject.dao.CarDao;
import by.nevar.dima.myproject.dao.impl.DefaultCarDao;
import by.nevar.dima.myproject.model.Car;
import by.nevar.dima.myproject.model.RoleCar;
import by.nevar.dima.myproject.service.CarService;

import java.util.List;

public class DefaultCarService implements CarService {

    private static class SingletonHolder {
        static final CarService HOLDER_INSTANCE = new DefaultCarService();
    }

    public static CarService getInstance() {
        return DefaultCarService.SingletonHolder.HOLDER_INSTANCE;
    }

    private CarDao carDao = DefaultCarDao.getInstance();

    @Override
    public Car saveCar(Car car) {
        return carDao.saveCar(car);
    }

    @Override
    public boolean updateCar(Car car) {
        return carDao.updateCar(car);
    }

    @Override
    public boolean deleteCar(String id) {
        return carDao.deleteCar(Long.parseLong(id));
    }

    @Override
    public List<Car> getAllCars() {
        return carDao.getAllCars();
    }

    @Override
    public List<Car> getByBrand(String brand) {
        return carDao.getByBrand(brand);
    }

    @Override
    public List<Car> getByModel(String model) {
        return carDao.getByModel(model);
    }

    @Override
    public List<Car> getByRoleCar(String role) {
        return carDao.getByRoleCar(RoleCar.valueOf(role));
    }
}
