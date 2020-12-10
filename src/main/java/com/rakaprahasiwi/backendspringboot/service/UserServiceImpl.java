package com.rakaprahasiwi.backendspringboot.service;

import com.rakaprahasiwi.backendspringboot.model.User;
import com.rakaprahasiwi.backendspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    //web security config
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(final User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    //save can be used for update and insert operation in Database
    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllByName(String name) {
        return userRepository.findAllByName(name);
    }

    @Override
    public List<User> findAllbyNameNative(String name) {
        return userRepository.findAllbyNameNative(name);
    }

    @Override
    public List<User> findRole(String role) {
        return userRepository.findRole(role);
    }

    @Override
    public List<String> findUsermaneRole(String role) {
        return userRepository.findUsermaneRole(role);
    }

    @Override
    public Long numberOfUsers() {
        return userRepository.count();
    }
}
