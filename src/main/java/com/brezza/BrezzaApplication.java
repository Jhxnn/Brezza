package com.brezza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.brezza.models")
@SpringBootApplication
public class BrezzaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrezzaApplication.class, args);
	}

}
