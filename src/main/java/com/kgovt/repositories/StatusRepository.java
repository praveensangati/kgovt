package com.kgovt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kgovt.models.PaymentDetails;
import com.kgovt.models.Status;

public interface StatusRepository extends JpaRepository<Status, Integer> {

	Status findByMobile(Long mobile);

}
