package by.nevar.dima.myproject.servise.impl;

import by.nevar.dima.myproject.model.AuthUser;
import by.nevar.dima.myproject.model.Role;
import by.nevar.dima.myproject.service.SecurityService;
import by.nevar.dima.myproject.service.impl.DefaultSecurityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultSecureServiceTest {

    @Test
    void loginTest(){
        DefaultSecurityService serviceImpl;
        SecurityService service;

        service = Mockito.mock(DefaultSecurityService.class);
        serviceImpl = new DefaultSecurityService();
//        when(service.login("admin", "admin")).thenReturn(login.getLogin());
//        assertEquals(serviceImpl.login("admin", "admin"), );
    }

    @Mock
    DefaultSecurityService service;


    @Test
    public void loginTestMock(){
        AuthUser login = new AuthUser("test", "test", Role.ADMINISTRATOR, "123");
        when(service.login("admin", "admin")).thenReturn(login);
    }
}

