package com.project.petcaremanager.dtos.schedulingServiceDtos.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CreateSchedulingServiceDto(
        @NotBlank(message = "Informe o agendamento")
        Long schedulingId,
        @NotBlank(message = "Informe o serviços do agendamento")
        List<Long> servicesId
) {
}
