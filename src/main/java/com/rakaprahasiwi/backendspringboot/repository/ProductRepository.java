package com.rakaprahasiwi.backendspringboot.repository;

import com.rakaprahasiwi.backendspringboot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
