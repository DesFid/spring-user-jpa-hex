package com.services.users.app.infrastructure.adapters.output.persitence.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.services.users.app.domain.model.User;
import com.services.users.app.infrastructure.adapters.output.persitence.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {
  UserEntity toUserEntity(User user);

  User toUser(UserEntity entity);

  List<User> toUserList(List<UserEntity> entityList);
}
