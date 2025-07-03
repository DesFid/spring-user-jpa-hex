package com.services.users.app.infrastructure.adapters.input.rest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.services.users.app.domain.exceptions.UserEmailAlreadyExistsException;
import com.services.users.app.domain.exceptions.UserNotFoundException;
import com.services.users.app.domain.model.ErrorResponse;
import java.lang.Exception;
import static com.services.users.app.utils.ErrorCatalog.USER_NOT_FOUND;
import static com.services.users.app.utils.ErrorCatalog.INVALID_USER;
import static com.services.users.app.utils.ErrorCatalog.GENERIC_ERROR;
import static com.services.users.app.utils.ErrorCatalog.USER_EMAIL_ALREADY_EXISTS;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalControllerAdvice {

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(UserNotFoundException.class)
  public ErrorResponse handleUserNotFoundException() {
    return ErrorResponse.builder()
      .code(USER_NOT_FOUND.getCode())
      .message(USER_NOT_FOUND.getMessage())
      .timestamp(LocalDateTime.now())
      .build();
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
    BindingResult result = exception.getBindingResult();
    return ErrorResponse.builder()
      .code(INVALID_USER.getCode())
      .message(INVALID_USER.getMessage())
      .timestamp(LocalDateTime.now())
      .details(result.getFieldErrors()
        .stream()
        .map(fieldError -> fieldError.getDefaultMessage())
        .collect(Collectors.toList()))
      .build();
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(Exception.class)
  public ErrorResponse handlerGenericError(Exception exception) {
    return ErrorResponse.builder()
      .code(GENERIC_ERROR.getCode())
      .message(GENERIC_ERROR.getMessage())
      .details(Collections.singletonList(exception.getMessage()))
      .timestamp(LocalDateTime.now())
      .build();
  }
  
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(UserEmailAlreadyExistsException.class)
  public ErrorResponse handleUserEmailAlreadyExistsException(
      UserEmailAlreadyExistsException e) {
    return ErrorResponse.builder()
        .code(USER_EMAIL_ALREADY_EXISTS.getCode())
        .build();
  }
}