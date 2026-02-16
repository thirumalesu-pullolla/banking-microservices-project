package com.bank.registration_service.service;

import com.bank.registration_service.entity.Customer;
import com.bank.registration_service.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

	private final CustomerRepository repository;

	public RegistrationService(CustomerRepository repository) {
		this.repository = repository;
	}

	public Customer register(Customer customer) {

		if(repository.findByEmail(customer.getEmail()).isPresent()) {
			throw new RuntimeException("Email already exists");
		}

		return repository.save(customer);
	}
}
