package com.bankgemini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BankGemini1Application {

	public static void main(String[] args) {
		SpringApplication.run(BankGemini1Application.class, args);
		
		System.out.println("hello wrking");
	}

}
