package com.project.petcaremanager.dtos.professionalDtos.request;

import jakarta.validation.constraints.NotBlank;

public record CreateProfessionalDto(
        @NotBlank(message = "Informe o usuário ligado a esse professional")
        Long userId,
        @NotBlank(message = "Informe a especialidade desse profissional")
        String specialty,
        String crmv,
        @NotBlank(message = "Informe o telefone do profissional")
        String phone
) {
}
