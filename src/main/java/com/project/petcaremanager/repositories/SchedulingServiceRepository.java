package com.project.petcaremanager.repositories;

import com.project.petcaremanager.models.SchedulingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchedulingServiceRepository extends JpaRepository<SchedulingService, Long> {
    Page<SchedulingService> findAllBySchedulingId(Long schedulingId, Pageable pageable);
    void deleteAllBySchedulingId(Long schedulingId);
}
