package com.rakaprahasiwi.backendspringboot.service;

import com.rakaprahasiwi.backendspringboot.model.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction saveTransaction(Transaction transaction);

    Long numberOfTransactions();

    List<Transaction> findAllTransactions();
}
