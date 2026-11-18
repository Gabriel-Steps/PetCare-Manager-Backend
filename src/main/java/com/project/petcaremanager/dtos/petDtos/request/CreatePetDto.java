package com.project.petcaremanager.dtos.petDtos.request;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreatePetDto(
        @NotBlank(message = "O identificador do usuário é obrigatório")
        Long clientId,
        @NotBlank(message = "O campo nome é obrigatório")
        String name,
        @NotBlank(message = "O campo espécie é obrigatório")
        String species,
        String race,
        @NotBlank(message = "O campo sexo é obrigatório")
        String sex,
        @NotBlank(message = "O campo adestrado é obrigatório")
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
