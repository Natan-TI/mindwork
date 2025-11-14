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

import com.mindwork.dto.UserRegisterRequest;
import com.mindwork.dto.UserResponse;
import com.mindwork.dto.UserUpdateRequest;
import com.mindwork.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {
	private final UserService service;
	
	public UserController(UserService service) {
		this.service = service;
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserResponse> register(
	        @Valid @RequestBody UserRegisterRequest request
	) {
	    UserResponse response = service.register(request);

	    URI location = URI.create("/users/" + response.id());
	    return ResponseEntity.created(location).body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getById(@PathVariable("id") UUID id) {
		return ResponseEntity.ok(service.getById(id));
	}
	
	@GetMapping
	public ResponseEntity<List<UserResponse>> listAll() {
		return ResponseEntity.ok(service.listAllUsers());
	}
	
	@GetMapping("/organization/{orgId}")
	public ResponseEntity<List<UserResponse>> listByOrganization(@PathVariable("orgId") UUID orgId) {
		return ResponseEntity.ok(service.listByOrganization(orgId));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserResponse> update(@PathVariable("id") UUID id, @Valid @RequestBody UserUpdateRequest req) {
		return ResponseEntity.ok(service.update(id, req));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
