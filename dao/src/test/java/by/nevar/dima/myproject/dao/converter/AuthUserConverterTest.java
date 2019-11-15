package by.nevar.dima.myproject.dao.converter;

import by.nevar.dima.myproject.dao.entity.AuthUserEntity;
import by.nevar.dima.myproject.model.AuthUser;
import by.nevar.dima.myproject.model.RoleUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AuthUserConverterTest {

    @Test
    void fromEntityNull() {
        final AuthUser authUser = AuthUserConverter.fromEntity(null);
        assertNull(authUser);
    }

    @Test
    void fromEntityNotNull() {
        final AuthUserEntity authUserEntity = new AuthUserEntity();
        authUserEntity.setId(1L);
        authUserEntity.setRole(RoleUser.USER);
        authUserEntity.setPassword("2");
        authUserEntity.setLogin("log");

        final AuthUser authUser = AuthUserConverter.fromEntity(authUserEntity);

        assertNotNull(authUser);
        assertEquals(authUser.getId(), authUserEntity.getId());
        assertEquals(authUser.getLogin(), authUserEntity.getLogin());
        assertEquals(authUser.getPassword(), authUserEntity.getPassword());
        assertEquals(authUser.getRoleUser(), authUserEntity.getRole());
    }

    @Test
    void toEntityNull() {
        final AuthUserEntity authUser = AuthUserConverter.toEntity(null);
        assertNull(authUser);
    }

    @Test
    void toEntityNotNull() {
        final AuthUser authUser = new AuthUser(1L, "log2", "2", RoleUser.USER);

        final AuthUserEntity authUserEntity = AuthUserConverter.toEntity(authUser);

        assertNotNull(authUser);
        assertEquals(authUser.getId(), authUserEntity.getId());
        assertEquals(authUser.getLogin(), authUserEntity.getLogin());
        assertEquals(authUser.getPassword(), authUserEntity.getPassword());
        assertEquals(authUser.getRoleUser(), authUserEntity.getRole());
    }
}
