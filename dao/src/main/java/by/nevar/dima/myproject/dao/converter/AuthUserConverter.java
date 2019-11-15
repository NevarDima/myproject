package by.nevar.dima.myproject.dao.converter;

import by.nevar.dima.myproject.dao.entity.AuthUserEntity;
import by.nevar.dima.myproject.model.AuthUser;

public class AuthUserConverter {
    public static AuthUser fromEntity(AuthUserEntity authUser) {
        if (authUser == null) {
            return null;
        }
        return new AuthUser(
                authUser.getId(),
                authUser.getLogin(),
                authUser.getPassword(),
                authUser.getRole());
    }

    public static AuthUserEntity toEntity(AuthUser authUser) {
        if (authUser == null) {
            return null;
        }
        final AuthUserEntity authUserEntity = new AuthUserEntity();
        authUserEntity.setId(authUser.getId());
        authUserEntity.setLogin(authUser.getLogin());
        authUserEntity.setPassword(authUser.getPassword());
        authUserEntity.setRole(authUser.getRoleUser());
        return authUserEntity;
    }
}
