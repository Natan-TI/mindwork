package com.mindwork.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mindwork.dto.UserRegisterRequest;
import com.mindwork.dto.UserResponse;
import com.mindwork.dto.UserUpdateRequest;
import com.mindwork.entities.Organization;
import com.mindwork.entities.User;
import com.mindwork.enums.Role;
import com.mindwork.exceptions.BusinessException;
import com.mindwork.exceptions.ResourceNotFoundException;
import com.mindwork.repositories.OrganizationRepository;
import com.mindwork.repositories.UserRepository;
import com.mindwork.services.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository userRepository,
                           OrganizationRepository organizationRepository) {
        this.userRepository = userRepository;
        this.organizationRepository = organizationRepository;
    }

	@Override
	public UserResponse register(UserRegisterRequest request) {
		if(userRepository.existsByEmail(request.email())) {
			throw new BusinessException("Email já está em uso!");
		}
		
		Organization org = organizationRepository.findById(request.organizationId())
				.orElseThrow(() -> new ResourceNotFoundException("Organization not found with id: " + request.organizationId()));
		
		User user = new User();
		user.setName(request.name());
		user.setEmail(request.email());
		user.setPassword(passwordEncoder.encode(request.password()));
		user.setRole(Role.EMPLOYEE);
		user.setOrganization(org);
		
		User saved = userRepository.save(user);
		return toResponse(saved);
	}

	@Override
	@Transactional(readOnly = true)
	public UserResponse getById(UUID id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
		return toResponse(user);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserResponse> listByOrganization(UUID organizationId) {
		return userRepository.findAllByOrganizationId(organizationId, null)
				.stream()
				.map(this::toResponse)
				.toList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserResponse> listAllUsers() {
		return userRepository.findAll()
				.stream()
				.map(this::toResponse)
				.toList();
	}

	@Override
	public UserResponse update(UUID id, UserUpdateRequest request) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
		
		if(request.name() != null && !request.name().isBlank()) {
			user.setName(request.name());
		}
		if(request.email() != null && !request.email().isBlank() && !request.email().equals(user.getEmail())) {
			if (userRepository.existsByEmail(request.email())) {
				throw new BusinessException("Email já está em uso");
			}
			user.setEmail(request.email());
		}
		
		User saved = userRepository.save(user);
		return toResponse(saved);
		
	}

	@Override
	public void delete(UUID id) {
		if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
	}
	
	private UserResponse toResponse(User u) {
	    return new UserResponse(
	            u.getId(),
	            u.getName(),
	            u.getEmail(),
	            u.getRole(),
	            u.getOrganization().getId(),
	            u.getCreatedAt(),
	            u.getConsentGivenAt()
	    );
    }
	
}
