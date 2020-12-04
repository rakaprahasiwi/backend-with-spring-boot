package com.rakaprahasiwi.backendspringboot.controller;

import com.rakaprahasiwi.backendspringboot.helper.UtilHelper;
import com.rakaprahasiwi.backendspringboot.jwt.JwtTokenProvider;
import com.rakaprahasiwi.backendspringboot.model.Product;
import com.rakaprahasiwi.backendspringboot.model.Transaction;
import com.rakaprahasiwi.backendspringboot.model.User;
import com.rakaprahasiwi.backendspringboot.service.ProductService;
import com.rakaprahasiwi.backendspringboot.service.TransactionService;
import com.rakaprahasiwi.backendspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController extends UtilHelper {
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
        Map map = new HashMap();
        if (userService.findByUsername(user.getUsername()) != null) {
            map.put("message", "Username " + user.getUsername() + " sudah digunakan.");
            return new ResponseEntity<>(map, HttpStatus.CONFLICT);
        }

        userService.saveUser(user);
        map.put("message", user.getUsername() + " berhasil didaftarkan.");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @GetMapping("/api/user/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Map map = new HashMap();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
//            User user1 = userService.findByUsername(user.getUsername());
            map.put("token", jwtTokenProvider.generateToken(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()))));
            map.put("message", "Berhasil login");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            map.put("message", "Username or password invalid");
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
        Map map;
        List<Product> productList = productService.findAllProduct();

        if (productList.size() > 0) {
            map = setOutputData(productList.size(), "Product found", productList);
        } else {
            map = setOutputData(productList.size(), "Product not found.", productList);
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
