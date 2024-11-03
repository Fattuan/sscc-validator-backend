package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Konfigurationsklasse für CORS-Einstellungen, wird automatisch von SpringBoot erkannt
@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Methode zur Konfiguration der CORS-Richtlinien für spezifische Endpunkte
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Erlaubt CORS für alle Endpunkte, die mit "/api/" beginnen
                .allowedOrigins("http://localhost:3000") // Erlaubt Anfragen nur von diesem Origin (z. B. React-Frontend auf Port 3000)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Erlaubte HTTP-Methoden
                .allowCredentials(true) // Ermöglicht das Senden von Cookies und Authentifizierungsinformationen
                .maxAge(3600); // Dauer, für die die CORS-Antworten im Cache gespeichert werden (in Sekunden)
    }
}
