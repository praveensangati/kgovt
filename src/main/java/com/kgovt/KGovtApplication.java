package com.kgovt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages="com.kgovt.repositories")
@SpringBootApplication
public class KGovtApplication extends SpringBootServletInitializer{
	
	public static void main(String[] args) {
		SpringApplication.run(KGovtApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(KGovtApplication.class);
	}
}
