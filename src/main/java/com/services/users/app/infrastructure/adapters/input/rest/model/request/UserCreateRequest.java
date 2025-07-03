package com.services.users.app.infrastructure.adapters.input.rest.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {
  
  @NotBlank(message = "Field firstName cannot be empty or null.")
  private String firstName;

  @NotBlank(message = "Field lastName cannot be empty or null.")
  private String lastName;

  @NotBlank(message = "Field email cannot be empty or null.")
  private String email;

  @NotNull(message = "Field Integer cannot be null.")
  private Integer age;

}
