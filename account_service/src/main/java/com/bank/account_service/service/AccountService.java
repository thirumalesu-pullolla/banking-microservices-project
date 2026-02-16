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
}
