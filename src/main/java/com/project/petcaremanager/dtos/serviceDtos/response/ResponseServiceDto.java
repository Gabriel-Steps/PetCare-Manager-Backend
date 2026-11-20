package com.project.petcaremanager.dtos.serviceDtos.response;

import com.project.petcaremanager.models.enums.ServiceCategory;

import java.math.BigDecimal;

public record ResponseServiceDto(
        Long id,
        String name,
        ServiceCategory category,
        String description,
        int duration,
        BigDecimal basePrice,
        boolean requiresVeterinarian
) {
}
