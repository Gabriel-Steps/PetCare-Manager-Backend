package com.project.petcaremanager.dtos.petDtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreatePetDto(
        @NotNull(message = "Informe o dono do pet") @Positive
        Long clientId,
        @NotBlank(message = "O campo nome é obrigatório")
        String name,
        @NotBlank(message = "O campo espécie é obrigatório")
        String species,
        String race,
        @NotBlank(message = "O campo sexo é obrigatório")
        String sex,
        @NotNull(message = "Informe se o pet é castrado")
        boolean neutered,
        LocalDate dateOfBirth,
        BigDecimal weight,
        @NotBlank(message = "O campo porte é obrigatório")
        String size,
        String color,
        String allergy,
        String temperament,
        Long numberMicroship
) {
}
