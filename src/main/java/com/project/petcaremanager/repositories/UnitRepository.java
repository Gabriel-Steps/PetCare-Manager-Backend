package com.project.petcaremanager.repositories;

import com.project.petcaremanager.models.Unit;
import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<Unit, Long> {
    boolean existsById(@NonNull Long id);
}
