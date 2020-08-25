package com.kgovt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kgovt.models.PaymentDetails;
import com.kgovt.repositories.PaymentDetailsRepository;
import com.kgovt.utils.AppConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentDetailsService extends AppConstants{

	@Autowired
	private PaymentDetailsRepository paymentDetailsRepository;
		
	public PaymentDetails savePaymentDetails(PaymentDetails paymentDetails)  {
		paymentDetails = paymentDetailsRepository.save(paymentDetails);
		return paymentDetails;
	}
	

	
}
