package com.mindwork.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindwork.dto.OrganizationRequest;
import com.mindwork.dto.OrganizationResponse;
import com.mindwork.entities.Organization;
import com.mindwork.exceptions.ResourceNotFoundException;
import com.mindwork.repositories.OrganizationRepository;
import com.mindwork.services.OrganizationService;


@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {
	
	private final OrganizationRepository repository;
	
	public OrganizationServiceImpl(OrganizationRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<OrganizationResponse> listAll() {
		return repository.findAll()
				.stream()
				.map(this::toResponse)
				.toList();
	}

	@Override
	public OrganizationResponse create(OrganizationRequest req) {
		Organization org = new Organization();
		org.setName(req.name());
		Organization saved = repository.save(org);
		return toResponse(saved);
	}

	@Override
	@Transactional(readOnly = true)
	public OrganizationResponse getById(UUID id) {
		Organization org = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Organization not found with id: " + id));
				
		return toResponse(org);
	}

	@Override
	public OrganizationResponse update(UUID id, OrganizationRequest req) {
		Organization org = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Organization not found with id: " + id));
		
		org.setName(req.name());
		Organization updated = repository.save(org);
		return toResponse(updated);
	}

	@Override
	public void delete(UUID id) {
		if(!repository.existsById(id)) {
			throw new ResourceNotFoundException("Organization not found with id: " + id);
		}
		repository.deleteById(id);
	}

	private OrganizationResponse toResponse(Organization org) {
		return new OrganizationResponse(
				org.getId(),
				org.getName(),
				org.getCreatedAt()
		);
		
	}

}
