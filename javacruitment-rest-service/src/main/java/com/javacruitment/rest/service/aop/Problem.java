package com.javacruitment.rest.service.aop;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Problem {
	@Schema(description = "HTTP status code", example = "123")
	private final int status;
	@Schema(description = "Reason phrase", example = "Error")
	private final String title;
	@Schema(description = "Detailed description", example = "Something went wrong.")
	private final String detail;
}
