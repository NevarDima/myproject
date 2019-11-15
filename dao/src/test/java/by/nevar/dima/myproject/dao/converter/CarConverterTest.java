package by.nevar.dima.myproject.dao.converter;

import by.nevar.dima.myproject.dao.entity.CarEntity;
import by.nevar.dima.myproject.model.Car;
import by.nevar.dima.myproject.model.RoleCar;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarConverterTest {

    @Test
    void fromEntityNull() {
        final Car car = CarConverter.fromEntity(null);
        assertNull(car);
    }

    @Test
    void fromEntityNotNull() {
        final CarEntity carEntity = new CarEntity();
        carEntity.setId(1L);
        carEntity.setBrand("Ferrari");
        carEntity.setModel("Lagonda");
        carEntity.setRoleCar(RoleCar.COUPE);
        carEntity.setBlocked(true);

        final Car car = CarConverter.fromEntity(carEntity);

        assertNotNull(car);
        assertEquals(car.getId(), carEntity.getId());
        assertEquals(car.getBrand(), carEntity.getBrand());
        assertEquals(car.getModel(), carEntity.getModel());
        assertEquals(car.getRoleCar(), carEntity.getRoleCar());
        assertEquals(car.getIsBlocked(),carEntity.isBlocked());
    }

    @Test
    void toEntityNull() {
        final CarEntity car = CarConverter.toEntity(null);
        assertNull(car);
    }

    @Test
    void toEntityNotNull() {
        final Car car = new Car(1L, "Ferrari", "Lagonda", RoleCar.COUPE, false);

        final CarEntity carEntity = CarConverter.toEntity(car);

        assertNotNull(car);
        assertEquals(car.getId(), carEntity.getId());
        assertEquals(car.getBrand(), carEntity.getBrand());
        assertEquals(car.getModel(), carEntity.getModel());
        assertEquals(car.getRoleCar(), carEntity.getRoleCar());
        assertEquals(car.getIsBlocked(),carEntity.isBlocked());
    }
}
