package com.bank.account_service.controller;

import com.bank.account_service.dto.CreateAccountRequest;
import com.bank.account_service.entity.Account;
import com.bank.account_service.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/accounts")
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping
    public Account createAccount(@RequestBody CreateAccountRequest request) {
        return service.createAccount(request.getCustomerId());
    }

    @GetMapping("/{accountId}/balance")
    public Double getBalance(@PathVariable Long accountId) {
        return service.getBalance(accountId);
    }

    @GetMapping("/customer/{customerId}")
    public List<Account> getAccountsByCustomer(@PathVariable Long customerId) {
        return service.getAccountsByCustomer(customerId);
    }

    @PostMapping("/{accountId}/deposit")
    public Account deposit(@PathVariable Long accountId,
                           @RequestParam Double amount) {
        return service.deposit(accountId, amount);
    }

    @PostMapping("/{accountId}/withdraw")
    public Account withdraw(@PathVariable Long accountId,
                            @RequestParam Double amount) {
        return service.withdraw(accountId, amount);
    }


}
