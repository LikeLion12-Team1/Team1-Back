package com.magnetic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class MagneticApplication {

	public static void main(String[] args) {
		SpringApplication.run(MagneticApplication.class, args);
	}

}
