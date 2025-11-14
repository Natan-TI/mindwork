package com.mindwork.dto;

import java.util.UUID;

import com.mindwork.enums.DataSourceType;
import com.mindwork.enums.MoodState;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record MoodEntryRequest(
		@NotNull UUID userId,
		@NotNull MoodState mood,
		@Min(0) @Max(10) Short stressLevel,
		@Min(0) Double sleepHours,
		@Min(0) Integer screenTimeMinutes,
		@Size(max=280) String notes,
		@NotNull DataSourceType source,
		@DecimalMin("0.0") @DecimalMax("1.0") Double confidence
) {}
