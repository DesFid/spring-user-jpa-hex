package com.services.users.app.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.services.users.app.application.ports.output.UserPersistencePort;
import com.services.users.app.application.utils.TestUtils;
import com.services.users.app.domain.exceptions.UserEmailAlreadyExistsException;
import com.services.users.app.domain.exceptions.UserNotFoundException;
import com.services.users.app.domain.model.User;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
  @Mock
  private UserPersistencePort userPersistencePort;

  @InjectMocks
  private UserService userService;

  @Test
  void testFindById() {

  }

  @Test
  void givenExistingUserId_whenFindById_thenReturnsUser() {
    // Inicializacion
    User userMock = TestUtils.buildUserMock();
    Long idToSearch = 1L;
    when(userPersistencePort.findById(anyLong()))
        .thenReturn(Optional.of(userMock));
    // Evaluacion de metodo
    User user = userService.findById(idToSearch);
    // Comprobaciones o aserciones
    assertNotNull(user);
    assertEquals(idToSearch, user.getId());
    assertEquals(userMock.getFirstName(), user.getFirstName());
    verify(userPersistencePort, times(1)).findById(idToSearch);
  }

  @Test
  void givenNonExistingUserId_whenFindById_thenThrowsUserNotFoundException() {
    Long idToSearch = 1L;
    when(userPersistencePort.findById(anyLong()))
        .thenReturn(Optional.empty());
    assertThrows(UserNotFoundException.class, () -> userService.findById(idToSearch));
    verify(userPersistencePort, times(1)).findById(idToSearch);
  }

  @Test
  void givenExistingUsers_whenFindAll_thenReturnsAllUsers() {
    User userMock = TestUtils.buildUserMock();
    when(userPersistencePort.findAll())
        .thenReturn(Collections.singletonList(userMock));
    List<User> userList = userService.findAll();
    assertFalse(userList.isEmpty());
    assertEquals(1, userList.size());
    assertEquals(1L, userList.get(0).getId());
    assertEquals(userMock.getFirstName(), userList.get(0).getFirstName());
    verify(userPersistencePort, times(1)).findAll();
  }

  @Test
  void getNewUserWithUniqueEmail_whenSave_thenPersistAndReturnStudent() {
    User userMock = User.builder()
        .id(1L)
        .firstName("Adrian")
        .lastName("Torres")
        .email("hombredehielosm@gmail.com")
        .age(30)
        .build();
    when(userPersistencePort.existsByEmail(anyString()))
        .thenReturn(Boolean.FALSE);
    when(userPersistencePort.save(any(User.class)))
        .thenReturn(TestUtils.buildUserMock());
    User userSaved = userService.save(userMock);
    assertNotNull(userSaved);
    assertEquals(1L, userSaved.getId());
    assertEquals("Adrian", userSaved.getFirstName());
    verify(userPersistencePort, times(1)).existsByEmail("hombredehielosm@gmail.com");
    verify(userPersistencePort, times(1)).save(userMock);
  }

  @Test
  void getNewUserWithExistingEmail_whenSave_thenThrowsEmailAlreadyExists() {
    User userMock = User.builder()
        .id(1L)
        .firstName("Adrian")
        .lastName("Torres")
        .email("hombredehielosm@gmail.com")
        .age(30)
        .build();
    when(userPersistencePort.existsByEmail(anyString()))
        .thenReturn(Boolean.TRUE);
    assertThrows(UserEmailAlreadyExistsException.class, () -> userService.save(userMock));
    verify(userPersistencePort, times(1)).existsByEmail("hombredehielosm@gmail.com");
  }

  @Test
  void testUpdate() {

  }
}
