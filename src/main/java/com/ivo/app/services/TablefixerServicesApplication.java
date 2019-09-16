package com.ivo.app.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TablefixerServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TablefixerServicesApplication.class, args);
	}

}
