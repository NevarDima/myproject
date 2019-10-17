package by.nevar.dima.myproject.model;

public class User {
    private long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private long authId;

    public User(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public User(long id, String firstName, String lastName, String phone, String email, long authId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.authId = authId;
    }

    public long getId(){
        return id;
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

    public long getAuthId() {
        return authId;
    }
}
