package com.mindwork.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindwork.entities.MoodEntry;

@Repository
public interface MoodEntryRepository extends JpaRepository<MoodEntry, UUID>{
	List<MoodEntry> findByUserIdOrderByCreatedAtDesc(UUID userId);
	
	Page<MoodEntry> findByUserId(UUID userId, Pageable pageable);
	
	Page<MoodEntry> findByUserOrganizationId(UUID organizationId, Pageable pageable);
}
