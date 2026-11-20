package com.project.petcaremanager.repositories;

import com.project.petcaremanager.models.Scheduling;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {
    Page<Scheduling> findAllByPet_Client_Id(Long clientId, Pageable pageable);
    Page<Scheduling> findAllByPetId(Long petId, Pageable pageable);
    Page<Scheduling> findAllByUnitId(Long unitId, Pageable pageable);
    Page<Scheduling> findAllByProfessionalId(Long professionalId, Pageable pageable);
    Page<Scheduling> findAllByReceptionistId(Long receptionistId, Pageable pageable);
}
