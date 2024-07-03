package com.example.Runner.repository;

import com.example.Runner.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    User findFirstByUsername(String username);
    User findByUsername(String username);
}
