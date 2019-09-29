package by.nevar.dima.myproject.servise.impl;

import by.nevar.dima.myproject.model.AuthUser;
import by.nevar.dima.myproject.model.Role;
import by.nevar.dima.myproject.service.impl.DefaultSecurityService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultSecureServiceTest {

    @Test
    void loginTest(){
        DefaultSecurityService service = new DefaultSecurityService();

        AuthUser login = service.login("admin", "admin");

        Role role = login.getRole();

        assertEquals ("ADMINISTRATOR",role);
    }
}

