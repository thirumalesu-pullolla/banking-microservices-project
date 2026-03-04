package com.bank.bill_service.service;

import com.bank.bill_service.client.AccountClient;
import com.bank.bill_service.entity.BillPayment;
import com.bank.bill_service.repository.BillRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class BillService {

    private final BillRepository repository;
    private final AccountClient accountClient;

    public BillService(BillRepository repository,
                       AccountClient accountClient) {
        this.repository = repository;
        this.accountClient = accountClient;
    }

    public BillPayment payBill(Long accountId,
                               String billType,
                               Double amount) {

        accountClient.withdraw(accountId, amount);

        BillPayment bill = new BillPayment();
        bill.setAccountId(accountId);
        bill.setBillType(billType);
        bill.setAmount(amount);
        bill.setPaymentDate(LocalDateTime.now());

        return repository.save(bill);
    }

}
