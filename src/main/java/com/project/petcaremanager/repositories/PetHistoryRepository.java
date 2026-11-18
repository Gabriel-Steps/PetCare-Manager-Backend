package com.project.petcaremanager.repositories;

import com.project.petcaremanager.models.PetHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetHistoryRepository extends JpaRepository<PetHistory, Long> {
    Page<PetHistory> findAllByPetId(Long petId);
}
