package com.javacruitment.rest.service.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
class RootContextToSwaggerUiRedirector {

	@GetMapping("/")
	String redirectToSwaggerUi() {
		return "redirect:swagger-ui.html";
	}
}
