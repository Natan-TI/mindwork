package com.mindwork.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindwork.enums.DataSourceType;
import com.mindwork.enums.MoodState;

public record MoodEntryResponse(
		UUID id,
	    UUID userId,
	    MoodState mood,
	    Short stressLevel,
	    Double sleepHours,
	    Integer screenTimeMinutes,
	    String notes,
	    DataSourceType source,
	    Double confidence,
	    LocalDateTime createdAt
) {}
