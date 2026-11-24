package com.project.petcaremanager.dtos.unitOperationDtos.request;

import jakarta.validation.constraints.NotBlank;

import java.util.TimeZone;

public record CreateUnitOperationsDto(
        @NotBlank(message = "Informe a unidade desse horário")
        Long unitId,
        @NotBlank(message = "Informe o dia da semana que a unidade irá atuar")
        String dayOfWeek,
        @NotBlank(message = "Informe o horário de inicio da unidade")
        TimeZone startTime,
        @NotBlank(message = "Informe o horário de fim fechamento da unidade")
        TimeZone endTime
) {
}
