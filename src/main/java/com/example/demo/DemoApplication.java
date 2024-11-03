package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Hauptklasse für die Spring Boot-Anwendung
@SpringBootApplication // Diese Annotation markiert die Klasse als Spring Boot-Anwendung und aktiviert die automatische Konfiguration.
public class DemoApplication {

    // Der Einstiegspunkt der Anwendung
    public static void main(String[] args) {
        // Startet die Anwendung mithilfe der SpringApplication-Klasse
        SpringApplication.run(DemoApplication.class, args);
    }
}
