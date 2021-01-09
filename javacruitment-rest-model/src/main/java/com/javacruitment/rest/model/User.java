package com.javacruitment.rest.model;

import java.time.LocalDateTime;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public class User extends UserUpsert {

	@Schema(description = "Id", example = "25b1228e-5880-4e5d-a859-0c10d0431a4d")
	private UUID id;

	@Schema(description = "Creation date", example = "2020-12-03T15:33:40.121336")
	private LocalDateTime creationDate;
}
