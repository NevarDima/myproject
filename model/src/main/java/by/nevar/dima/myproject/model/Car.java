package by.nevar.dima.myproject.model;

public class Car {

    private long id;
    private String brand;
    private String model;
    private RoleCar roleCar;
    private boolean isBlocked;

    public Car(){

    }

    public Car(String brand, String model, RoleCar roleCar){
        this.brand = brand;
        this.model = model;
        this.roleCar = roleCar;
    }

    public Car(long id, String brand, String model, RoleCar roleCar, boolean isBlocked){
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.roleCar = roleCar;
        this.isBlocked = isBlocked;
    }

    public long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public RoleCar getRoleCar() {
        return roleCar;
    }

    public boolean getIsBlocked (){
        return isBlocked;
    }
}
