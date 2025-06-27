package com.services.users.app.utils;

import lombok.Getter;

@Getter
public enum ErrorCatalog {
  USER_NOT_FOUND("ERR_USER_001", "User not found."),
  INVALID_USER("ERR_USER_002", "Invalid users parameters."),
  GENERIC_ERROR("ERR_GEN_001", "An unexpected error occurred.");
  private final String code;
  private final String message;
  ErrorCatalog(String code, String message) {
    this.code = code;
    this.message = message;
  }
}
