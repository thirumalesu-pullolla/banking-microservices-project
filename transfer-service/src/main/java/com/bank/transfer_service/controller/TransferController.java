package com.bank.transfer_service.controller;

import com.bank.transfer_service.entity.Transfer;
import com.bank.transfer_service.service.TransferService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfer")            //@RequestMapping("/api/transfer")
public class TransferController {

    private final TransferService service;

    public TransferController(TransferService service) {
        this.service = service;
    }

    @PostMapping
    public Transfer transfer(@RequestParam Long fromAccount,
                             @RequestParam Long toAccount,
                             @RequestParam Double amount) {

        return service.transfer(fromAccount, toAccount, amount);
    }
}
