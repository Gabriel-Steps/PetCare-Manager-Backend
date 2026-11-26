package com.project.petcaremanager.dtos.schedulingServiceDtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record CreateSchedulingServiceDto(
        @NotNull(message = "Informe o agendamento") @Positive
        Long schedulingId,
        @NotNull(message = "Informe o serviços do agendamento")
        List<Long> servicesId
) {
}
