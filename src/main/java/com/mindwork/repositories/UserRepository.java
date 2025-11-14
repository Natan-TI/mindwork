package com.mindwork.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindwork.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{

	Optional<User> findByEmail(String email);
	
	boolean existsByEmail(String email);
	
	List<User> findAllByOrganizationId(UUID organizationId);
	
	Page<User> findAllByOrganizationId(UUID organizationId, Pageable pageable);
}
