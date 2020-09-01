package com.kgovt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kgovt.models.AcceptedCandidates;

public interface AcceptedRepository extends JpaRepository<AcceptedCandidates, Integer> {

	

}
