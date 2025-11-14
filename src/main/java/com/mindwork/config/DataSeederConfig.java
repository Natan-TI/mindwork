package com.mindwork.config;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mindwork.entities.Organization;
import com.mindwork.entities.User;
import com.mindwork.enums.Role;
import com.mindwork.repositories.OrganizationRepository;
import com.mindwork.repositories.UserRepository;

@Configuration
public class DataSeederConfig {

    @Bean
    CommandLineRunner initData(UserRepository userRepository,
                               OrganizationRepository organizationRepository,
                               BCryptPasswordEncoder passwordEncoder) {
        return args -> {

            String adminEmail = "admin@mindwork.com";

            if (userRepository.existsByEmail(adminEmail)) {
                return;
            }

            Organization org = new Organization();
            org.setName("MindWork Admin Org");
            Organization savedOrg = organizationRepository.save(org);

            User admin = new User();
            admin.setName("MindWork Admin");
            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);
            admin.setOrganization(savedOrg);
            admin.setConsentGivenAt(LocalDateTime.now());

            userRepository.save(admin);

            System.out.println("==== ADMIN SEED CRIADO ====");
            System.out.println("Email: " + adminEmail);
            System.out.println("Senha: admin123");
        };
    }
}
