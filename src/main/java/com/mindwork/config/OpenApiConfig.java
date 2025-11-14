package com.mindwork.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "MindWork API",
                version = "v1",
                description = "API para monitoramento de bem-estar emocional de colaboradores, com foco no futuro do trabalho e saúde mental.",
                contact = @Contact(
                        name = "MindWork Team",
                        email = "contato@mindwork.com"
                )
        ),
        servers = {
                @Server(
                        url = "http://localhost:8080",
                        description = "Ambiente local"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class OpenApiConfig {
}
