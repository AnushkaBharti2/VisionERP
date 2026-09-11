package com.visionerp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean OpenAPI visionErpOpenAPI() {
        return new OpenAPI().info(new Info().title("VisionERP API").version("1.0.0")
            .description("Secure REST API for the VisionERP workplace management platform."));
    }
}
