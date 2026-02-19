package com.bank.transfer_service.service;

import com.bank.transfer_service.entity.Transfer;
import com.bank.transfer_service.repository.TransferRepository;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class TransferService {

    private final TransferRepository repository;
    private final RestTemplate restTemplate;
    private final CircuitBreakerFactory circuitBreakerFactory;

    public TransferService(TransferRepository repository,
                           RestTemplate restTemplate,
                           CircuitBreakerFactory circuitBreakerFactory) {
        this.repository = repository;
        this.restTemplate = restTemplate;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    public Transfer transfer(Long fromAccount,
                             Long toAccount,
                             Double amount) {

        return circuitBreakerFactory.create("accountServiceCB")
                .run(() -> executeTransfer(fromAccount, toAccount, amount),
                        throwable -> fallbackTransfer(fromAccount, toAccount, amount));
    }

    private Transfer executeTransfer(Long fromAccount,
                                     Long toAccount,
                                     Double amount) {

        // Withdraw
        restTemplate.postForObject(
                "http://ACCOUNT-SERVICE/accounts/"
                        + fromAccount
                        + "/withdraw?amount=" + amount,
                null,
                Object.class
        );

        // Deposit
        restTemplate.postForObject(
                "http://ACCOUNT-SERVICE/accounts/"
                        + toAccount
                        + "/deposit?amount=" + amount,
                null,
                Object.class
        );

        Transfer transfer = new Transfer();
        transfer.setFromAccount(fromAccount);
        transfer.setToAccount(toAccount);
        transfer.setAmount(amount);
        transfer.setDate(java.time.LocalDateTime.now());

        return repository.save(transfer);
    }

    private Transfer fallbackTransfer(Long fromAccount,
                                      Long toAccount,
                                      Double amount) {

        System.out.println("Account Service is down! Executing fallback.");

        Transfer transfer = new Transfer();
        transfer.setFromAccount(fromAccount);
        transfer.setToAccount(toAccount);
        transfer.setAmount(0.0);
        transfer.setDate(java.time.LocalDateTime.now());

        return transfer;
    }
}
