package com.project.petcaremanager.repositories;

import com.project.petcaremanager.models.UnitOperation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UnitOperationRepository extends JpaRepository<UnitOperation, Long> {
    List<UnitOperation> findAllByUnitId(Long unitId);
    Optional<UnitOperation> findByIdAndUnitId(Long operationId, Long unitId);
}
