package com.infomanager.repo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.infomanager.entities.User;
import com.infomanager.repositories.UserRepo;

@SpringBootTest
class UserRepoTest {
	
	@Autowired
	private UserRepo userRepo;

	@Test
	void findByEmailTest() {
		
		 // Arrange - set up data for testing
        User user = new User();
        user.setUserId("333");
        user.setEmail("test@example.com");
        user.setName("Test User");
        userRepo.save(user); // Persist the user
        
     // Act - call the method to be tested
        Optional<User> testUser = userRepo.findByEmail("test@example.com");

     // Assert - verify the result
        assertTrue(testUser.isPresent());
        assertEquals("test@example.com", testUser.get().getEmail());
        assertEquals("Test User", testUser.get().getName());
	}
	
    @Test
    void testFindByEmailNotFound() {
        // Act - call the method with an email that doesn't exist
        Optional<User> foundUser = userRepo.findByEmail("nonexistent@example.com");

        // Assert - verify the result
        assertFalse(foundUser.isPresent());
    }
    
    @AfterEach
    void tearDown() {
    	System.out.println("Tearing Down");
    }
    
    @BeforeEach
    void setUp() {
    	System.out.println("Setting Up");
    }
    

}
