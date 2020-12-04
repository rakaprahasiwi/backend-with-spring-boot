package com.rakaprahasiwi.backendspringboot.controller;

import com.rakaprahasiwi.backendspringboot.helper.UtilHelper;
import com.rakaprahasiwi.backendspringboot.model.Product;
import com.rakaprahasiwi.backendspringboot.model.StringResponse;
import com.rakaprahasiwi.backendspringboot.model.Transaction;
import com.rakaprahasiwi.backendspringboot.model.User;
import com.rakaprahasiwi.backendspringboot.service.ProductService;
import com.rakaprahasiwi.backendspringboot.service.TransactionService;
import com.rakaprahasiwi.backendspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class AdminController extends UtilHelper {
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private TransactionService transactionService;

    @PutMapping("/api/admin/user-update")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        User existUser = userService.findByUsername(user.getUsername());
        if (existUser != null && !existUser.getId().equals(user.getId())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/api/admin/user-delete")
    public ResponseEntity<?> deleteUser(@RequestBody User user) {
        userService.deleteUser(user.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/admin/user-all")
    public ResponseEntity<?> findAllUser() {
        Map map;
        List<User> userList = userService.findAllUsers();
        if (userList.size() > 0) {
            map = setOutputData(userList.size(), "User ditemukan", userList);
        } else {
            map = setOutputData(userList.size(), "Data not found.", userList);
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/api/admin/user-number")
    public ResponseEntity<?> numberOfUsers() {
        Long number = userService.numberOfUsers();
        StringResponse stringResponse = new StringResponse();
        stringResponse.setResponse(number.toString());

        //to return it, we will use string response because long is not a suitable response for rest api
        return new ResponseEntity<>(stringResponse, HttpStatus.OK);
    }

    @PostMapping("/api/admin/product-create")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
    }

    @PutMapping("/api/admin/product-update")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.updateProduct(product), HttpStatus.CREATED);
    }

    @PostMapping("/api/admin/product-delete")
    public ResponseEntity<?> deleteProduct(@RequestBody Product product) {
        productService.deleteProduct(product.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/admin/product-all")
    public ResponseEntity<?> findAllProducts() {
        Map map;
        List<Product> productList = productService.findAllProduct();
        if (productList.size() > 0) {
            map = setOutputData(productList.size(), "Product ditemukan.", productList);
        } else {
            map = setOutputData(productList.size(), "Product not found.", productList);
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/api/admin/product-number")
    public ResponseEntity<?> numberOfProducts() {
        Long number = productService.numberOfProducts();
        StringResponse stringResponse = new StringResponse();
        stringResponse.setResponse(number.toString());
        return new ResponseEntity<>(stringResponse, HttpStatus.OK);
    }

    @GetMapping("/api/admin/transaction-all")
    public ResponseEntity<?> findAllTransactions() {
        Map map;
        List<Transaction> transactionList = transactionService.findAllTransactions();
        if (transactionList.size() > 0) {
            map = setOutputData(transactionList.size(), "Transaction ditemukan.", transactionList);
        } else {
            map = setOutputData(transactionList.size(), "Transaction not found.", transactionList);
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/api/admin/transaction-number")
    public ResponseEntity<?> numberOfTransactions() {
        Long number = transactionService.numberOfTransactions();
        StringResponse stringResponse = new StringResponse();
        stringResponse.setResponse(number.toString());

        return new ResponseEntity<>(stringResponse, HttpStatus.OK);
    }
}
