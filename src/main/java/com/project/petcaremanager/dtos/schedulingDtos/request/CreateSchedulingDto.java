package com.project.petcaremanager.dtos.schedulingDtos.request;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateSchedulingDto(
        @NotBlank(message = "Informe o pet que terá no agendamento")
        Long petId,
        @NotBlank(message = "Informe o profissional que realizará o serviço")
        Long professionalId,
        @NotBlank(message = "Informe o(a) recepcionista que marcou o agendamento")
        Long receptionistId,
        @NotBlank(message = "Informe a unidade que será reliazado o serviço")
        Long unitId,
        @NotBlank(message = "Informe o horário de inicio da consulta")
        LocalDateTime startDateTime,
        @NotBlank(message = "Informe o horário previsto para finalizar a consulta")
        LocalDateTime endDateTime,
        @NotBlank(message = "Informe o preço final do agendamento")
        BigDecimal finalPrice,
        String observation
) {
}
