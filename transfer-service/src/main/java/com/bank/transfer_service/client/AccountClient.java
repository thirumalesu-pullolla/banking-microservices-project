package com.bank.transfer_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "account-service")  //@FeignClient(name = "account-service",url = "http://localhost:8083")
public interface AccountClient {

    @PostMapping("/accounts/{accountId}/withdraw")
    Object withdraw(@PathVariable Long accountId,
                    @RequestParam Double amount);

    @PostMapping("/accounts/{accountId}/deposit")
    Object deposit(@PathVariable Long accountId,
                   @RequestParam Double amount);
}
