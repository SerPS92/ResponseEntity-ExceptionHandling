package com.example.ResponseEntityExceptionHandling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.SpringVersion;

@SpringBootApplication
public class ResponseEntityExceptionHandlingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResponseEntityExceptionHandlingApplication.class, args);
		System.out.println(SpringVersion.getVersion());
	}

}
