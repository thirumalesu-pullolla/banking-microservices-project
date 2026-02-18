package com.bank.bill_service.service;

import com.bank.bill_service.entity.BillPayment;
import com.bank.bill_service.repository.BillRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class BillService {

    private final BillRepository repository;
    private final RestTemplate restTemplate;

    public BillService(BillRepository repository,
                       RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public BillPayment payBill(Long accountId,
                               String billType,
                               Double amount) {

        // Withdraw money from account
        restTemplate.postForObject(
                "http://localhost:8083/api/accounts/"
                        + accountId
                        + "/withdraw?amount=" + amount,
                null,
                Object.class
        );

        BillPayment bill = new BillPayment();
        bill.setAccountId(accountId);
        bill.setBillType(billType);
        bill.setAmount(amount);
        bill.setPaymentDate(LocalDateTime.now());

        return repository.save(bill);
    }
}
