package com.project.petcaremanager.dtos.professionalDtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateProfessionalDto(
        @NotNull(message = "Informe o usuário ligado a esse professional") @Positive
        Long userId,
        @NotBlank(message = "Informe a especialidade desse profissional")
        String specialty,
        String crmv,
        @NotBlank(message = "Informe o telefone do profissional")
        String phone
) {
}
