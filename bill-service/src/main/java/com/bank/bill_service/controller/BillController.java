package com.bank.bill_service.controller;

import com.bank.bill_service.entity.BillPayment;
import com.bank.bill_service.service.BillService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bills")
public class BillController {

    private final BillService service;

    public BillController(BillService service) {
        this.service = service;
    }

    @PostMapping
    public BillPayment payBill(@RequestParam Long accountId,
                               @RequestParam String billType,
                               @RequestParam Double amount) {

        return service.payBill(accountId, billType, amount);
    }
}
