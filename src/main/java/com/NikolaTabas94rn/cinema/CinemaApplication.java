package com.NikolaTabas94rn.cinema;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CinemaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}

	@Bean
	public OpenAPI freelanceOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("Cinema API")
						.description("REST API for cinema application")
						.version("v0.0.1"));
	}

}
