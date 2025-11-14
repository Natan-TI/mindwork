package com.mindwork.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record OrganizationResponse(
		UUID id,
		String name,
		LocalDateTime createdAt
) {
}
