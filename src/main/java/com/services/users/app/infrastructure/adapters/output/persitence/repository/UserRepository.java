package com.services.users.app.infrastructure.adapters.output.persitence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.services.users.app.domain.model.User;
import com.services.users.app.infrastructure.adapters.output.persitence.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
  User findByEmail(String email);
  Boolean existsByEmail(String email);
}
