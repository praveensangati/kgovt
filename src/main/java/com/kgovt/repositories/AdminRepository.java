package com.kgovt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kgovt.models.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

	public Admin findByRegion(String region);

}
