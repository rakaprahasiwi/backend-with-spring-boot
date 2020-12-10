package com.rakaprahasiwi.backendspringboot.repository;

import com.rakaprahasiwi.backendspringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //findBy + username
    Optional<User> findByUsername(String username);

    @Query("FROM User WHERE name = ?1")
    List<User> findAllByName(String name);

    @Query(value = "SELECT * FROM user WHERE name=?1", nativeQuery = true)
    List<User> findAllbyNameNative(String name);

    @Query(value = "SELECT * FROM user WHERE role=?1", nativeQuery = true)
    List<User> findRole(String role);

    @Query(value = "SELECT username FROM user WHERE role=?1", nativeQuery = true)
    List<String> findUsermaneRole(String role);
}
