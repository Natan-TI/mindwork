package com.mindwork.controllers;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindwork.dto.OrganizationRequest;
import com.mindwork.dto.OrganizationResponse;
import com.mindwork.services.OrganizationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/organizations")
@Validated
public class OrganizationController {
	
	private final OrganizationService service;
	
	public OrganizationController(OrganizationService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<OrganizationResponse> create(
	        @Valid @RequestBody OrganizationRequest request
	) {
	    OrganizationResponse response = service.create(request);

	    URI location = URI.create("/organizations/" + response.id());
	    return ResponseEntity.created(location).body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrganizationResponse> getById(@PathVariable("id") UUID id) {
		return ResponseEntity.ok(service.getById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<OrganizationResponse>> listAll() {
		return ResponseEntity.ok(service.listAll());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<OrganizationResponse> update(@PathVariable("id") UUID id, @Valid @RequestBody OrganizationRequest req) {
		return ResponseEntity.ok(service.update(id, req));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
