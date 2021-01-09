package com.javacruitment.rest.service.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RestConfiguration {

	@Bean
	OpenAPI openAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Javacruitment API")
						.version("1.0.0"));
	}
}
