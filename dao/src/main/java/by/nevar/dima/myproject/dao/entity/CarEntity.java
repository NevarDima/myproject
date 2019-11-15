package by.nevar.dima.myproject.dao.entity;


import by.nevar.dima.myproject.model.RoleCar;

import javax.persistence.*;

@Entity
@Table(name = "car")
public class CarEntity {


    private Long id;
    private String brand;
    private String model;
    private RoleCar roleCar;
    private boolean blocked;

    public CarEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Enumerated(EnumType.STRING)
    public RoleCar getRoleCar() {
        return roleCar;
    }

    public void setRoleCar(RoleCar roleCar) {
        this.roleCar = roleCar;
    }

//    public boolean getIfBlocked(){
//        return blocked;
//    }

    public void setBlocked(boolean blocked){
        this.blocked = blocked;
    }

    public boolean isBlocked() {
        return blocked;
    }
}
