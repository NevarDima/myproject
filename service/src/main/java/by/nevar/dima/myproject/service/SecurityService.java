package by.nevar.dima.myproject.service;

import by.nevar.dima.myproject.model.AuthUser;

public interface SecurityService {

    AuthUser getById(String id);

    AuthUser login(String login, String password);

    AuthUser saveAuthUser(AuthUser user);

    boolean updateAuthUser(AuthUser user);

    boolean deleteAuthUser(String id);

}
