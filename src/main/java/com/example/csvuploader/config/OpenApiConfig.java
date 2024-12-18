package com.eventpilot.config;

import java.io.InputStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        try {
            InputStream yamlInputStream = getClass().getClassLoader().getResourceAsStream("openapi.yml");
            if (yamlInputStream == null) {
                throw new IllegalStateException("Unable to find openapi.yml in resources.");
            }

            ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
            return yamlMapper.readValue(yamlInputStream, OpenAPI.class);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load OpenAPI configuration from openapi.yml", e);
        }
    }
}