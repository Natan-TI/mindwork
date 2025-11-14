package com.mindwork.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import com.mindwork.enums.DataSourceType;
import com.mindwork.enums.MoodState;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "tb_mood_entry")
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MoodEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@EqualsAndHashCode.Include
	private UUID id;
	
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private MoodState mood;

	@Min(0)
    @Max(10)
	private Short stressLevel;
	
	@Min(0)
	private Double sleepHours;
	
	@Min(0)
	private Integer screenTimeMinutes;
	
	@Size(max = 280)
	private String notes;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private DataSourceType source;
	
	@DecimalMin("0.0")
    @DecimalMax("1.0")
	private Double confidence;
	
	@NotNull
	private LocalDateTime createdAt;
	
	@PrePersist
	public void prePersist() {
	    if (createdAt == null) {
	        createdAt = LocalDateTime.now();
	    }
	}

}
