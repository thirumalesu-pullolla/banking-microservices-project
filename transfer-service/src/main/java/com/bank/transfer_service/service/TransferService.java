package com.bank.transfer_service.service;

import com.bank.transfer_service.entity.Transfer;
import com.bank.transfer_service.repository.TransferRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class TransferService {

    private final TransferRepository repository;
    private final RestTemplate restTemplate;

    public TransferService(TransferRepository repository,
                           RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public Transfer transfer(Long fromAccount,
                             Long toAccount,
                             Double amount) {

        // Withdraw from sender
        restTemplate.postForObject(
                "http://localhost:8083/api/accounts/"
                        + fromAccount
                        + "/withdraw?amount=" + amount,
                null,
                Object.class
        );

        // Deposit to receiver
        restTemplate.postForObject(
                "http://localhost:8083/api/accounts/"
                        + toAccount
                        + "/deposit?amount=" + amount,
                null,
                Object.class
        );

        Transfer transfer = new Transfer();
        transfer.setFromAccount(fromAccount);
        transfer.setToAccount(toAccount);
        transfer.setAmount(amount);
        transfer.setDate(LocalDateTime.now());

        return repository.save(transfer);
    }
}
