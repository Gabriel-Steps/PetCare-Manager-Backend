package com.project.petcaremanager.repositories;

import com.project.petcaremanager.models.Professional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionalRepository extends JpaRepository<Professional, Long> {
    Page<Professional> findAllByUser_Unit_Id(Long unitId, Pageable pageable);
}
