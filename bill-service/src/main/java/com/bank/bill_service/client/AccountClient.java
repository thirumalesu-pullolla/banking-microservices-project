package com.bank.bill_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "account-service") //@FeignClient(name = "account-service",url = "http://localhost:8083")
public interface AccountClient {

    @PostMapping("/api/accounts/{accountId}/withdraw")
    Object withdraw(@PathVariable Long accountId,
                    @RequestParam Double amount);
}
