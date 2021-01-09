package com.javacruitment.rest.model;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@ToString
public class UserUpsert {

	@Schema(description = "Name", example = "John", required = true)
	@NotBlank(message = "Name cannot be empty.")
	private String username;

	@Schema(description = "Email", example = "john@doe.com", required = true)
	@NotBlank(message = "Email cannot be empty.")
	private String email;
}
