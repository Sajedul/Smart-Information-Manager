package com.infomanager.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.infomanager.entities.User;
import com.infomanager.repositories.UserRepo;
import com.infomanager.services.impl.UserServiceImpl;

import jakarta.transaction.Transactional;

//test using mock service

@SpringBootTest
public class UserServiceTest {

  @Mock
  private UserRepo userRepo; // Mocking the UserRepo

  @InjectMocks
  private UserServiceImpl userService; // Injecting the mock into the UserService

  @BeforeEach
  void setUp() {
      MockitoAnnotations.openMocks(this); // Initialize mocks
  }

  @Test
  public void testIsUserExistByEmail() {
      String email = "test@example.com";

      // Arrange: Setting up the mock behavior
      when(userRepo.findByEmail(email)).thenReturn(Optional.of(new User()));

      // Act: Calling the method to be tested
      boolean exists = userService.isUserExistByEmail(email);

      // Assert: Verifying the result
      assertTrue(exists);
      verify(userRepo).findByEmail(email); // Verifying that the repository method was called
  }

  @Test
  public void testGetUserByEmail() {
      String email = "test@example.com";
      User user = new User();
      user.setEmail(email);
      user.setName("testUser");

      // Arrange: Setting up the mock behavior
      when(userRepo.findByEmail(email)).thenReturn(Optional.of(user));

      // Act: Calling the method to be tested
      User result = userService.getUserByEmail(email);

      // Assert: Verifying the result
      assertNotNull(result);
      assertEquals(user.getEmail(), result.getEmail());
      verify(userRepo).findByEmail(email); // Verifying that the repository method was called
  }
}

//Test for using real Service

/*
 * @SpringBootTest // This will load the full Spring ApplicationContext
 * 
 * @ActiveProfiles("test") // Use application-test.properties for the test
 * profile
 * 
 * @Transactional class UserServiceTest {
 * 
 * @Autowired private UserService userService; // Test the real service
 * 
 * @Autowired private UserRepo userRepo; // Real repository for actual DB
 * interaction
 * 
 * @Test public void testIsUserExistByEmail_UserExists() { // Given: Save a user
 * to the H2 database User user = new User();
 * user.setUserId(UUID.randomUUID().toString()); user.setName("testUser");
 * user.setEmail("test1@example.com"); userRepo.save(user);
 * 
 * // When: Call the service method boolean userExists =
 * userService.isUserExistByEmail("test1@example.com");
 * 
 * // Then: Verify that the user exists assertTrue(userExists); }
 * 
 * @Test public void testIsUserExistByEmail_UserDoesNotExist() { // When: Call
 * the service method without saving any user boolean userExists =
 * userService.isUserExistByEmail("nonexistent@example.com");
 * 
 * // Then: Verify that the user does not exist assertFalse(userExists); }
 * 
 * @Test public void testGetUserByEmail_UserFound() { // Given: Save a user to
 * the H2 database User user = new User();
 * user.setUserId(UUID.randomUUID().toString()); user.setName("testUser");
 * user.setEmail("test2@example.com"); userRepo.save(user);
 * 
 * // When: Call the service method User foundUser =
 * userService.getUserByEmail("test2@example.com");
 * 
 * // Then: Verify that the correct user is returned assertNotNull(foundUser);
 * assertEquals("test2@example.com", foundUser.getEmail()); }
 * 
 * @Test public void testGetUserByEmail_UserNotFound() { // When: Call the
 * service method for a non-existing user
 * assertThrows(IllegalArgumentException.class, () -> {
 * userService.getUserByEmail("nonexistent@example.com"); }); }
 * 
 * }
 */

