package com.mindwork.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record OrganizationRequest (
		@NotBlank @Size(max = 20) String name
){}
