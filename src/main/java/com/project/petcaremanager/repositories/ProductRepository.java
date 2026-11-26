package com.project.petcaremanager.repositories;

import com.project.petcaremanager.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByUnitId(Long unitId, Pageable pageable);
}
