package com.project.petcaremanager.dtos.vaccinationRecordDtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record CreateVaccinationRecordDto(
        @NotBlank(message = "Informe o nome da vacina")
        String name,
        @NotNull(message = "Informe qual o pet recebeu a vacina") @Positive
        Long petId,
        @NotBlank(message = "Informe que doenças essa vacina previne")
        String coveredConditions,
        @NotNull(message = "Informe o dia da vacinação")
        LocalDate vaccinationDay,
        @NotBlank(message = "Informe o número do lote da vacina")
        String lotNumber,
        @NotBlank(message = "Informe o nome do fabricante da vacina")
        String manufacturer,
        LocalDate boosterVaccine
) {
}
