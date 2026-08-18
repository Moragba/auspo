package com.auspo.backend.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${MEINE_IP:http://localhost}")
    private String meineIp;

    @Value("${MEIN_PORT:5173}")
    private String meinPort;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // Erlaubt Anfragen von deinem Vite-Dev-Server:
                .allowedOrigins(meineIp + ":" + meinPort) 
                // Erlaubt alle gängigen HTTP-Methoden inklusive OPTIONS
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}