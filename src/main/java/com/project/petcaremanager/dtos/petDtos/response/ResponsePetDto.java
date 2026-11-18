package com.project.petcaremanager.dtos.petDtos.response;

import com.project.petcaremanager.models.Client;
import com.project.petcaremanager.models.enums.PetSize;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ResponsePetDto(Long id,
                             String name,
                             String species,
                             String race,
                             String sex,
                             boolean neutered,
                             LocalDate dateOfBirth,
                             BigDecimal weight,
                             PetSize size,
                             String color,
                             String allergy,
                             String temperament,
                             Long numberMicroship) {
}
