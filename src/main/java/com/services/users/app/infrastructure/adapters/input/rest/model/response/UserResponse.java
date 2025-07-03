package com.services.users.app.infrastructure.adapters.input.rest.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String timestamp;
  private Integer age;
}
