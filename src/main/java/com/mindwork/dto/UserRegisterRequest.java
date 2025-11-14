package com.mindwork.dto;

import java.util.UUID;

import com.mindwork.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRegisterRequest(
		@NotNull UUID organizationId,
		@Size(max = 120) String name,
		@NotNull @Email @Size(max=180) String email,
		@NotBlank @Size(min=6, max=255) String password
		) {

}
