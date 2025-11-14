package com.mindwork.services;

import java.util.List;
import java.util.UUID;

import com.mindwork.dto.OrganizationRequest;
import com.mindwork.dto.OrganizationResponse;

public interface OrganizationService {
	
	List<OrganizationResponse> listAll();
	
	OrganizationResponse create(OrganizationRequest req);
	
	OrganizationResponse getById(UUID id);
	
	OrganizationResponse update(UUID id, OrganizationRequest req);

	void delete(UUID id);
}
