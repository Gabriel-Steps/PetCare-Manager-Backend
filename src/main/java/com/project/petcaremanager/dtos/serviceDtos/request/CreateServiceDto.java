package com.project.petcaremanager.dtos.serviceDtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateServiceDto(
        @NotBlank(message = "Informe o nome do serviço")
        String name,
        @NotBlank(message = "Informe a categoria do serviço")
        String category,
        String description,
        @NotNull(message = "Informe a duração do serviço") @Positive
        int duration,
        @NotNull(message = "Informe o preço base do serviço") @Positive
        BigDecimal basePrice,
        @NotNull(message = "Informe se o serviço requer presença de um veterinário")
        boolean requiresVeterinarian,
        @NotNull(message = "Informe a unidade que esse serviço será parte") @Positive
        Long unitId
) {
}
