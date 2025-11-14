package com.mindwork.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindwork.enums.Role;

public record UserResponse(
		UUID id,
	    String name,
	    String email,
	    Role role,
	    UUID organizationId,
	    LocalDateTime createdAt,
	    LocalDateTime consentGivenAt
) {}
