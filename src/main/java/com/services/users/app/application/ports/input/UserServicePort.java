package com.services.users.app.application.ports.input;

import java.util.List;

import com.services.users.app.domain.model.User;

public interface UserServicePort {

  User findById(Long id);
  List<User> findAll();
  User save(User user);
  User update(Long id, User user);
  void deleteById(Long id);
  
}
