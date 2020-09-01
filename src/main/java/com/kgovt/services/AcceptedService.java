package com.kgovt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kgovt.models.AcceptedCandidates;
import com.kgovt.models.Status;
import com.kgovt.repositories.AcceptedRepository;
import com.kgovt.utils.AppConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AcceptedService extends AppConstants{

	@Autowired
	private AcceptedRepository acceptedRepository;
		
	public AcceptedCandidates saveAcceptedCandidates(AcceptedCandidates acceptedCandidates)  {
		acceptedCandidates = acceptedRepository.save(acceptedCandidates);
		return acceptedCandidates;
	}

	
}
