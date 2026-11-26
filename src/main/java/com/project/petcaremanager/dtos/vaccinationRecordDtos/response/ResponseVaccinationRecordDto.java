package com.project.petcaremanager.dtos.vaccinationRecordDtos.response;

import java.time.LocalDate;

public record ResponseVaccinationRecordDto(
        Long id,
        String petName,
        String nameVaccination,
        String coveredConditions,
        LocalDate vaccinationDay,
        String lotNumber,
        String manufacturer,
        LocalDate boosterVaccine
) {
}
