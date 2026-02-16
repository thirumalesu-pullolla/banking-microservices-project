package com.bank.transfer_service.repository;

import com.bank.transfer_service.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository
        extends JpaRepository<Transfer, Long> {
}
