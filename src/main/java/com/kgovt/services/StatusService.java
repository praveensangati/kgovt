package com.kgovt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kgovt.models.PaymentDetails;
import com.kgovt.models.Status;
import com.kgovt.repositories.PaymentDetailsRepository;
import com.kgovt.repositories.StatusRepository;
import com.kgovt.utils.AppConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StatusService extends AppConstants {

	@Autowired
	private StatusRepository statusRepository;

	public Status saveStatus(Status status) {
		status = statusRepository.save(status);
		return status;
	}

	public Status findByMobile(Long mobile) {
		return statusRepository.findByMobile(mobile);
	}

	public void setStatusByApplicantNumber(String status, String comment,Long applicantNumber) {
		statusRepository.setStatusByApplicantNumber(status, comment,applicantNumber);
	}

	public Status findByApplicantNumber(Long applicantNumber) {
		
		return statusRepository.findByApplicantNumber(applicantNumber);
	}

}
