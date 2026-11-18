package com.project.petcaremanager.repositories;

import com.project.petcaremanager.models.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
    Page<Pet> findAllByClientId(Long clientId, Pageable pageable);
}
