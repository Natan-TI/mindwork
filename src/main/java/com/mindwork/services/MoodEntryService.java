package com.mindwork.services;

import java.util.List;
import java.util.UUID;

import com.mindwork.dto.MoodEntryRequest;
import com.mindwork.dto.MoodEntryResponse;

public interface MoodEntryService {

	MoodEntryResponse create(MoodEntryRequest request);
	
	MoodEntryResponse getByID(UUID id);
	
	List<MoodEntryResponse> listByUser(UUID userID);
	
	List<MoodEntryResponse> listByOrganization(UUID organizationID);
	
	void delete(UUID id);
}
