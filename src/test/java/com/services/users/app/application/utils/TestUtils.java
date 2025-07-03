package com.services.users.app.application.utils;

import java.time.LocalDate;


import com.services.users.app.domain.model.User;
import com.services.users.app.infrastructure.adapters.input.rest.model.request.UserCreateRequest;
import com.services.users.app.infrastructure.adapters.input.rest.model.response.UserResponse;
import com.services.users.app.infrastructure.adapters.output.persitence.entity.UserEntity;

public class TestUtils {
  public static User buildUserMock() {
    return User.builder()
    .id(1L)
    .firstName("Adrian")
    .lastName("Torres")
    .email("hombredehielosm@gmail.com")
    .age(30)
    .build();
  }

  public static UserEntity buildUserEntityMock() {
    return UserEntity.builder()
    .id(1L)
    .firstName("Adrian")
    .lastName("Torres")
    .email("hombredehielosm@gmail.com")
    .age(30)
    .build();
  }

  public static UserResponse buildUserResponseMock() {
    return UserResponse.builder()
    .id(1L)
    .firstName("Adrian")
    .lastName("Torres")
    .email("hombredehielosm@gmail.com")
    .age(30)
    .timestamp(LocalDate.now().toString())
    .build();
  }

  public static UserCreateRequest buildUserCreateRequestMock() {
    return UserCreateRequest.builder()
    .firstName("Adrian")
    .lastName("Torres")
    .email("hombredehielosm@gmail.com")
    .age(30)
    .build();
  }
}
