package com.project.petcaremanager.dtos.schedulingDtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateSchedulingDto(
        @NotNull(message = "Informe o pet que terá no agendamento") @Positive
        Long petId,
        @NotNull(message = "Informe o profissional que realizará o serviço") @Positive
        Long professionalId,
        @NotNull(message = "Informe o(a) recepcionista que marcou o agendamento") @Positive
        Long receptionistId,
        @NotNull(message = "Informe a unidade que será reliazado o serviço") @Positive
        Long unitId,
        @NotNull(message = "Informe o horário de inicio da consulta")
        LocalDateTime startDateTime,
        @NotNull(message = "Informe o horário previsto para finalizar a consulta")
        LocalDateTime endDateTime,
        @NotNull(message = "Informe o preço final do agendamento") @Positive
        BigDecimal finalPrice,
        String observation
) {
}
