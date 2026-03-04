package com.bank.transfer_service.service;

import com.bank.transfer_service.client.AccountClient;
import com.bank.transfer_service.entity.Transfer;
import com.bank.transfer_service.repository.TransferRepository;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class TransferService {

    private final TransferRepository repository;
    private final AccountClient accountClient;
    //private final RestTemplate restTemplate;
    //private final CircuitBreakerFactory circuitBreakerFactory;

    public TransferService(TransferRepository repository,
                           AccountClient accountClient
                            ) {
        this.repository = repository;
        this.accountClient = accountClient;

    }

    public Transfer transfer(Long fromAccount,
                             Long toAccount,
                             Double amount) {

        accountClient.withdraw(fromAccount, amount);
        accountClient.deposit(toAccount, amount);

        Transfer transfer = new Transfer();
        transfer.setFromAccount(fromAccount);
        transfer.setToAccount(toAccount);
        transfer.setAmount(amount);
        transfer.setDate(LocalDateTime.now());

        return repository.save(transfer);
    }

}
