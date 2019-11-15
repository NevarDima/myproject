package by.nevar.dima.myproject.dao.converter;

import by.nevar.dima.myproject.dao.entity.UserEntity;
import by.nevar.dima.myproject.model.User;

public class UserConverter {
    public static UserEntity toEntity(User user) {
        if (user == null) {
            return null;
        }
        final UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setPhone(user.getPhone());
        userEntity.setEmail(user.getEmail());
        userEntity.setAuthId(user.getAuthId());
        return userEntity;
    }

    public static User fromEntity(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        return new User(
                userEntity.getId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getPhone(),
                userEntity.getEmail(),
                userEntity.getAuthId());
    }
}
