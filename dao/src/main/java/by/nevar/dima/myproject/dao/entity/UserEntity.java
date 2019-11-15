package by.nevar.dima.myproject.dao.entity;


import javax.persistence.*;

@Entity
@Table(name = "user")
public class UserEntity {

    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Long authId;

    public UserEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "auth_id")
    public Long getAuthId() {
        return authId;
    }

    public void setAuthId(Long authId) {
        this.authId = authId;
    }


}
