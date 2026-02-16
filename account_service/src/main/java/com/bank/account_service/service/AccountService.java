package com.bank.account_service.service;

import com.bank.account_service.entity.Account;
import com.bank.account_service.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public Account createAccount(Long customerId) {

        Account account = new Account();
        account.setCustomerId(customerId);
        account.setBalance(0.0);
        account.setStatus("ACTIVE");

        return repository.save(account);
    }

    public Double getBalance(Long accountId) {

        Account account = repository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        return account.getBalance();
    }

    public List<Account> getAccountsByCustomer(Long customerId) {
        return repository.findByCustomerId(customerId);
    }

    public Account deposit(Long accountId, Double amount) {

        Account account = repository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setBalance(account.getBalance() + amount);

        return repository.save(account);
    }

    public Account withdraw(Long accountId, Double amount) {

        Account account = repository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if(account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - amount);

        return repository.save(account);
    }

}
