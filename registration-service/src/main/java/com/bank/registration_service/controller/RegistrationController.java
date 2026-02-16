package com.bank.registration_service.controller;

import com.bank.registration_service.entity.Customer;
import com.bank.registration_service.service.RegistrationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
public class RegistrationController {

	private final RegistrationService service;

	public RegistrationController(RegistrationService service) {
		this.service = service;
	}

	@PostMapping
	public Customer register(@RequestBody Customer customer) {
		return service.register(customer);
	}

	@GetMapping("/get")
	public String sayHello() {
		return "Hello everyone !";
	}
}
