package com.kgovt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.kgovt.models.Status;

public interface StatusRepository extends JpaRepository<Status, Integer> {

	Status findByMobile(Long mobile);
	
	@Modifying
	@Query("update Status s set s.status = ?1, s.comment = ?2 where s.applicantNumber = ?3")
	void setStatusByApplicantNumber(String status,String comment,Long applicantNumber);

	Status findByApplicantNumber(Long applicantNumber);

}
