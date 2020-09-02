package com.kgovt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kgovt.datatable.paging.Page;
import com.kgovt.datatable.paging.PagingRequest;
import com.kgovt.models.ApplicationDetailes;
import com.kgovt.services.ApplicationDetailesService;

@RestController
public class AppListRestController {
	@Autowired
	private ApplicationDetailesService appicationService;
	
	@PostMapping("/allApplicationData")
	public Page<ApplicationDetailes> list(@RequestBody PagingRequest pagingRequest,@RequestParam String region, String status) {
		return appicationService.getApplicationDetailess(pagingRequest,region, status);
	}
}
