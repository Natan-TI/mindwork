package com.mindwork.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindwork.entities.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, UUID>{
	Optional<Organization> findByName(String name);
	
	boolean existsByName(String name);
}
