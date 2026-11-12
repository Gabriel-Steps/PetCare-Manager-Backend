package com.project.petcaremanager.repositories;

import com.project.petcaremanager.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
