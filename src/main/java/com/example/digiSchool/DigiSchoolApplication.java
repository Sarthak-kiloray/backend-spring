package com.example.digiSchool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DigiSchoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigiSchoolApplication.class, args);
    }

    // Configure global CORS settings
    @Bean
    public WebMvcConfigurer configure() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry reg) {
                // Allow CORS requests from any origin for all endpoints
                reg.addMapping("/**").allowedOrigins("*");
            }
        };
    }
}