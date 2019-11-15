package by.nevar.dima.myproject.dao.converter;

import by.nevar.dima.myproject.dao.entity.CarEntity;
import by.nevar.dima.myproject.model.Car;

public class CarConverter {
    public static Car fromEntity(CarEntity car) {
        if (car == null) {
            return null;
        }
        return new Car(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getRoleCar(),
                car.isBlocked());
    }

    public static CarEntity toEntity(Car car) {
        if (car == null) {
            return null;
        }
        final CarEntity carEntity = new CarEntity();
        carEntity.setId(car.getId());
        carEntity.setBrand(car.getBrand());
        carEntity.setModel(car.getModel());
        carEntity.setRoleCar(car.getRoleCar());
        carEntity.setBlocked(car.getIsBlocked());
        return carEntity;
    }
}
