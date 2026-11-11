package com.project.petcaremanager.repositories;

import com.project.petcaremanager.models.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findAllByUserId(Long userId, Pageable pageable);
    Optional<Task> findByIdAndUserId(Long id, Long userId);
    Page<Task> findAllByCompletedAndUserId(boolean completed, Long userId, Pageable pageable);
}
