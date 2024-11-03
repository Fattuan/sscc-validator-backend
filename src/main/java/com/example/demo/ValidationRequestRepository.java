package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

// Repository-Schnittstelle für die Entität ValidationRequest
public interface ValidationRequestRepository extends JpaRepository<ValidationRequest, Long> {
    // Diese Schnittstelle erbt grundlegende CRUD-Operationen von JpaRepository.
	
    // Methoden wie save(), findById(), findAll(), deleteById() usw. werden automatisch bereitgestellt.
}
