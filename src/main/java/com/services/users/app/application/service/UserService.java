package com.services.users.app.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.services.users.app.application.ports.input.UserServicePort;
import com.services.users.app.application.ports.output.UserPersistencePort;
import com.services.users.app.domain.exceptions.UserEmailAlreadyExistsException;
import com.services.users.app.domain.exceptions.UserNotFoundException;
import com.services.users.app.domain.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserServicePort {

  private final UserPersistencePort userPersistencePort;

  @Override
  public User findById(Long id) {
    return userPersistencePort.findById(id)
      .orElseThrow(UserNotFoundException::new);
  }

  @Override
  public List<User> findAll() {
    return userPersistencePort.findAll();
  }

  @Override
  public User save(User user) {
    if(userPersistencePort.existsByEmail(user.getEmail())) {
      throw new UserEmailAlreadyExistsException(user.getEmail());
    }
    return userPersistencePort.save(user);
  }

  @Override
  public User update(Long id, User user) {
    return userPersistencePort.findById(id)
      .map(savedUser -> {
        savedUser.setFirstName(user.getFirstName());
        savedUser.setLastName(user.getLastName());
        savedUser.setEmail(user.getEmail());
        savedUser.setAge(user.getAge());
        return userPersistencePort.save(savedUser);
      })
      .orElseThrow(UserNotFoundException::new);
  }

  @Override
  public void deleteById(Long id) {
    if (userPersistencePort.findById(id).isEmpty()) {
      throw new UserNotFoundException();
    }
    userPersistencePort.deleteById(id);
  }
  
}
