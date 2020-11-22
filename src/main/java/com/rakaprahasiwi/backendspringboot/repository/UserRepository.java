package com.rakaprahasiwi.backendspringboot.repository;

import com.rakaprahasiwi.backendspringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //findBy + username
    Optional<User> findByUsername(String username);
}
