package com.project.petcaremanager.dtos.schedulingServiceDtos.response;

import com.project.petcaremanager.models.enums.ServiceCategory;

import java.math.BigDecimal;

public record ResponseSchedulingServiceDto(
        Long schedulingId,
        Long serviceId,
        String nameService,
        ServiceCategory serviceCategory,
        String description,
        int duration,
        BigDecimal basePrice,
        boolean requiresVeterinarian
) {
}
