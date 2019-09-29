package by.nevar.dima.myproject.service;

import by.nevar.dima.myproject.model.AuthUser;

public interface SecurityService {
    AuthUser login(String login, String password);
}
