package com.services.users.app;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.services.users.app.infrastructure.adapters.output.persitence.entity.UserEntity;
import com.services.users.app.infrastructure.adapters.output.persitence.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class UserServiceApplication implements CommandLineRunner {
	private final UserRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Override
	public void run (String... args) throws Exception {
		List<UserEntity> entities = Arrays.asList(
			new UserEntity(null, "Adrian", "Torres", "adriant@gmail.com", 20),
			new UserEntity(null, "Manuel", "Mulan", "anuelm@gmail.com", 24),
			new UserEntity(null, "Fab", "Tomalo", "fabt@gmail.com", 56),
			new UserEntity(null, "Kathy", "Gonzalez", "kathyg@gmail.com", 25)
		);
		repository.saveAll(entities);
	}

}
