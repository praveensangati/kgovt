package com.kgovt.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kgovt.models.ApplicationDetailes;

public interface ApplicationDetailesRepository extends JpaRepository<ApplicationDetailes, Integer> {

	ApplicationDetailes findByApplicantNumber(Long applicantNumber);
	
	Long countByMobile(Long mobile);
	
	@Query(value = "SELECT max(applicantNumber) from ApplicationDetailes")
	Long max();
	
	long deleteByApplicantNumber(long ApplicantNumber);
	
	List<ApplicationDetailes> findByPreOfCenter(String preOfCenter);
	
	@Query(value = "SELECT a from ApplicationDetailes a where a.preOfCenter=?1 and a.applicationStatus=?2")
	List<ApplicationDetailes> getByNames(String preOfCenter, String applicationStatus);
	
	
}
