package com.project.petcaremanager.dtos.schedulingDtos.response;

import com.project.petcaremanager.models.enums.SchedulingStatus;

import java.time.LocalDateTime;

public record ResponseSchedulingDto(
        Long id,
        String namePet,
        String nameProfessional,
        String nameReception,
        String nameUnit,
        LocalDateTime startDateTime,
        SchedulingStatus status
) {
}
