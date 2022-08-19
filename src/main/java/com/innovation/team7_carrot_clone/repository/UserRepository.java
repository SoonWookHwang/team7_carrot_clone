package com.innovation.team7_carrot_clone.repository;

import com.innovation.team7_carrot_clone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
}
