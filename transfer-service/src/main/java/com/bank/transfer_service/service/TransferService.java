package com.bank.transfer_service.service;

import com.bank.transfer_service.client.AccountClient;
import com.bank.transfer_service.entity.Transfer;
import com.bank.transfer_service.repository.TransferRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransferService {

    private final TransferRepository repository;
    private final AccountClient accountClient;

    public TransferService(TransferRepository repository,
                           AccountClient accountClient) {

        this.repository = repository;
        this.accountClient = accountClient;
    }

    @CircuitBreaker(name = "accountService", fallbackMethod = "fallbackTransfer")
    public Transfer transfer(Long fromAccount,
                             Long toAccount,
                             Double amount) {

        // Call Account Service
        accountClient.withdraw(fromAccount, amount);
        accountClient.deposit(toAccount, amount);

        Transfer transfer = new Transfer();
        transfer.setFromAccount(fromAccount);
        transfer.setToAccount(toAccount);
        transfer.setAmount(amount);
        transfer.setDate(LocalDateTime.now());

        return repository.save(transfer);
    }

    // Fallback method
    public Transfer fallbackTransfer(Long fromAccount,
                                     Long toAccount,
                                     Double amount,
                                     Throwable ex) {

        System.out.println("Account Service DOWN: " + ex.getMessage());

        throw new RuntimeException("Transfer failed. Please try again later.");
    }



}