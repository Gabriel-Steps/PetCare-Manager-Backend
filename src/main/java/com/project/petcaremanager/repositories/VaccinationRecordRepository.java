package com.project.petcaremanager.repositories;

import com.project.petcaremanager.models.VaccinationRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccinationRecordRepository extends JpaRepository<VaccinationRecord, Long> {
    Page<VaccinationRecord> findAllByPetId(Long petId, Pageable pageable);
}
