package com.services.users.app.application.ports.output;

import java.util.List;
import java.util.Optional;

import com.services.users.app.domain.model.User;

public interface UserPersistencePort {
  Optional<User> findById(Long id);
  List<User> findAll();
  User save(User user);
  void deleteById(Long id);
}
