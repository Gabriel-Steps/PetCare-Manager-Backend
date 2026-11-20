package com.project.petcaremanager.dtos.serviceDtos.request;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record CreateServiceDto(
        @NotBlank(message = "Informe o nome do serviço")
        String name,
        @NotBlank(message = "Informe a categoria do serviço")
        String category,
        String description,
        @NotBlank(message = "Informe a duração do serviço")
        int duration,
        @NotBlank(message = "Informe o preço base do serviço")
        BigDecimal basePrice,
        @NotBlank(message = "Informe se o serviço requer presença de um veterinário")
        boolean requiresVeterinarian
) {
}
