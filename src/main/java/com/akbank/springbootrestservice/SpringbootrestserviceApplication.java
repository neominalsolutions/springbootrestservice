package com.akbank.springbootrestservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Post API", description = "Akbank Post API", version = "1.0"))
public class SpringbootrestserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootrestserviceApplication.class, args);
	}

}
