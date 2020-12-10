package com.rakaprahasiwi.backendspringboot.service;

import com.rakaprahasiwi.backendspringboot.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    User updateUser(User user);

    void deleteUser(Long userId);

    User findByUsername(String username);

    List<User> findAllUsers();

    List<User> findAllByName(String name);

    List<User> findAllbyNameNative(String name);

    List<User> findRole(String role);

    List<String> findUsermaneRole(String role);

    Long numberOfUsers();
}
