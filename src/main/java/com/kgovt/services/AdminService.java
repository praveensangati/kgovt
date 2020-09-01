package com.kgovt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kgovt.models.Admin;
import com.kgovt.repositories.AdminRepository;
import com.kgovt.utils.AppConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdminService extends AppConstants{

	@Autowired
	private AdminRepository adminRepository;
		
	public Admin saveAdmin(Admin admin) {
		return adminRepository.save(admin);
	}
	
	public Admin findByAdminName(String adminName) {
		return adminRepository.findByAdminName(adminName);
	}

	public Admin findByRegion(String region) {
		return adminRepository.findByRegion(region);
	}

	
}
