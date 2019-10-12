package by.nevar.dima.myproject.model;

public class User {
    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String email;

    public User(Long id, String name, String surname, String phone, String email) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
    }

    public String getFirstName() {
        return name;
    }

    public String getLastName() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return name + "_" + surname;
    }
}
