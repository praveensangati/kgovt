package com.kgovt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kgovt.models.PaymentDetails;

public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Integer> {

	

}
