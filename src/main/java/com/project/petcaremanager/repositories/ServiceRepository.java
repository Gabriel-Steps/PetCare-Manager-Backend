package com.project.petcaremanager.repositories;

import com.project.petcaremanager.models.Services;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Services, Long> {
    Page<Services> findAllByUnitId(Long unitId, Pageable pageable);
}
