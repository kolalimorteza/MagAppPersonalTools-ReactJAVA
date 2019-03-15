package com.developer.architecture.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DeveloperArchitectureApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DeveloperArchitectureApplication.class, args);
	}
	

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DeveloperArchitectureApplication.class);
	}


}

