package com.project.petcaremanager.dtos.unitOperationDtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalTime;

public record CreateUnitOperationsDto(
        @NotNull(message = "Informe a unidade desse horário") @Positive
        Long unitId,
        @NotBlank(message = "Informe o dia da semana que a unidade irá atuar")
        String dayOfWeek,
        @NotNull(message = "Informe o horário de inicio da unidade")
        LocalTime startTime,
        @NotNull(message = "Informe o horário de fim fechamento da unidade")
        LocalTime endTime
) {
}
