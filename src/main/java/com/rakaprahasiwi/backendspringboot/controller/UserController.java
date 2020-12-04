package com.rakaprahasiwi.backendspringboot.controller;

import com.rakaprahasiwi.backendspringboot.jwt.JwtTokenProvider;
import com.rakaprahasiwi.backendspringboot.model.Role;
import com.rakaprahasiwi.backendspringboot.model.Transaction;
import com.rakaprahasiwi.backendspringboot.model.User;
import com.rakaprahasiwi.backendspringboot.service.ProductService;
import com.rakaprahasiwi.backendspringboot.service.TransactionService;
import com.rakaprahasiwi.backendspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/api/user/registration")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/api/user/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Map map = new HashMap();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            User user1 = userService.findByUsername(user.getUsername());
            map.put("token", jwtTokenProvider.generateToken(user1));
            map.put("status", "Berhasil login");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            map.put("username", user.getUsername());
            map.put("status", "Data invalid");
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    }

    @PostMapping("/api/user/purchase")
    public ResponseEntity<?> purchaseProduct(@RequestBody Transaction transaction) {
        transaction.setPurchaseDate(LocalDateTime.now());
        return new ResponseEntity<>(transactionService.saveTransaction(transaction), HttpStatus.OK);
    }

    @GetMapping("/api/user/products")
    public ResponseEntity<?> getAllProducts() {
        return new ResponseEntity<>(productService.findAllProduct(), HttpStatus.OK);
    }
}
