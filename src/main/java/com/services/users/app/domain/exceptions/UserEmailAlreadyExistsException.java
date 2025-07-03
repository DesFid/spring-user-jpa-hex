package com.services.users.app.domain.exceptions;

public class UserEmailAlreadyExistsException extends RuntimeException {
  public UserEmailAlreadyExistsException(String email) {
    super("Student email: " + email + " already exists!");
  }
}
