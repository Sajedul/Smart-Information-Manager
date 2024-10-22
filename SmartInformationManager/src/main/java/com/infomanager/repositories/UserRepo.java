package com.infomanager.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infomanager.entities.User;



@Repository
public interface UserRepo extends JpaRepository<User, String> {
	
    // custom query methods
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findByEmailToken(String id);
}
