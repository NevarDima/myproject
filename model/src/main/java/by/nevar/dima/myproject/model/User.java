package by.nevar.dima.myproject.model;

public class User {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

    public User(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return firstName + "_" + lastName;
    }
}
