package com.project.petcaremanager.repositories;

import com.project.petcaremanager.models.Services;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Services, Long> {
}
