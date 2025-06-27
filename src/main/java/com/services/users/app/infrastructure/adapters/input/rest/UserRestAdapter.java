package com.services.users.app.infrastructure.adapters.input.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.services.users.app.application.ports.input.UserServicePort;
import com.services.users.app.infrastructure.adapters.input.rest.mapper.UserRestMapper;
import com.services.users.app.infrastructure.adapters.input.rest.model.request.UserCreateRequest;
import com.services.users.app.infrastructure.adapters.input.rest.model.response.UserResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRestAdapter {
  private final UserServicePort servicePort;
  private final UserRestMapper restMapper;

  @GetMapping("/v1/api")
  public List<UserResponse> findAll() {
      return restMapper.toUserResponseList(servicePort.findAll());
  }

  @GetMapping("/v1/api/{id}")
  public UserResponse findById(@PathVariable Long id) {
      return restMapper.toUserResponse(servicePort.findById(id));
  }

  @PostMapping("/v1/api")
  public ResponseEntity<UserResponse> save(@Valid @RequestBody UserCreateRequest request) {
      return ResponseEntity.status(HttpStatus.CREATED)
        .body(restMapper.toUserResponse(servicePort.save(restMapper.toUser(request))));
  }
  
  @PutMapping("/v1/api/{id}")
  public UserResponse update(@PathVariable Long id, @Valid @RequestBody UserCreateRequest request) {
    return restMapper.toUserResponse(servicePort.update(id, restMapper.toUser(request)));
  }

  @DeleteMapping("/v1/api/{id}")
  public void delete(@PathVariable Long id) {
    servicePort.deleteById(id);
  }
}
