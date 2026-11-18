package com.project.petcaremanager.dtos.petHistory.response;

import com.project.petcaremanager.models.enums.ServiceCategory;

import java.time.LocalDate;

public record ResponsePetHistoryDto(
        Long id,
        String namePet,
        ServiceCategory category,
        String nameService,
        LocalDate serviceDay
) {
}
