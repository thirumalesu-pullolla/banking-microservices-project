package com.bank.bill_service.repository;

import com.bank.bill_service.entity.BillPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository
        extends JpaRepository<BillPayment, Long> {
}
