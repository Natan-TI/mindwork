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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindwork.dto.MoodEntryRequest;
import com.mindwork.dto.MoodEntryResponse;
import com.mindwork.services.MoodEntryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/mood-entries")
@Validated
public class MoodEntryController {
	
	private final MoodEntryService service;
	
	public MoodEntryController(MoodEntryService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<MoodEntryResponse> create(
	        @Valid @RequestBody MoodEntryRequest request
	) {
	    MoodEntryResponse response = service.create(request);

	    URI location = URI.create("/mood-entries/" + response.id());
	    return ResponseEntity.created(location).body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MoodEntryResponse> getById(@PathVariable("id") UUID id) {
		return ResponseEntity.ok(service.getByID(id));
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<MoodEntryResponse>> listByUser(@PathVariable("userId") UUID userId) {
		return ResponseEntity.ok(service.listByUser(userId));
	}
	
	@GetMapping("/organization/{orgId}")
	public ResponseEntity<List<MoodEntryResponse>> listByOrganization(@PathVariable("orgId") UUID orgId) {
		return ResponseEntity.ok(service.listByOrganization(orgId));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
