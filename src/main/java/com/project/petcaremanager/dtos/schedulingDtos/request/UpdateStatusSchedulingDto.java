package com.project.petcaremanager.dtos.schedulingDtos.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateStatusSchedulingDto(
        @NotBlank(message = "Informe o novo status do agendamento")
        String status
) {
}
