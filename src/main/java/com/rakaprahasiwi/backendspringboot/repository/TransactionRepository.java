package com.rakaprahasiwi.backendspringboot.repository;

import com.rakaprahasiwi.backendspringboot.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
