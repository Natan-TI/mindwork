package com.mindwork.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserUpdateRequest(
		@Size(max=120) String name,
		@Email @Size(max=180) String email
) {}
