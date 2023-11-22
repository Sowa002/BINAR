package com.binar.binarfud.repository;

import com.binar.binarfud.model.Bpay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BpayRepository extends JpaRepository<Bpay, Long> {

    public Bpay findByNopay(String nopay);
}
