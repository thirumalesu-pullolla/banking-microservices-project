package com.bank.authentication_service.repository;

import com.bank.authentication_service.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCredentialRepository
        extends JpaRepository<UserCredential, String> {
}
