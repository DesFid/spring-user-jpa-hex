package com.services.users.app.infrastructure.adapters.output.persitence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.services.users.app.application.ports.output.UserPersistencePort;
import com.services.users.app.domain.model.User;
import com.services.users.app.infrastructure.adapters.output.persitence.mapper.UserPersistenceMapper;
import com.services.users.app.infrastructure.adapters.output.persitence.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserPersistencePort {

  private final UserRepository repository;
  private final UserPersistenceMapper mapper;
  @Override
  public Optional<User> findById(Long id) {
    return repository.findById(id)
      .map(mapper::toUser);
  }

  @Override
  public List<User> findAll() {
    return mapper.toUserList(repository.findAll());
  }

  @Override
  public User save(User user) {
    return mapper.toUser(repository.save(mapper.toUserEntity(user)));
  }

  @Override
  public void deleteById(Long id) {
    repository.deleteById(id);
  }
  
}
