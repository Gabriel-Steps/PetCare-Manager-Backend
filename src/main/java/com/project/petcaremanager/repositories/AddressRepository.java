package com.project.petcaremanager.repositories;

import com.project.petcaremanager.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
