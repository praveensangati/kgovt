package com.kgovt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableJpaRepositories(basePackages="com.kgovt.repositories")
@SpringBootApplication
public class KGovtApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(KGovtApplication.class, args);
	}

}
