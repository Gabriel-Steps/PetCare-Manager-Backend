package com.project.petcaremanager.dtos.petHistory.request;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CreatePetHistoryDto(
        @NotBlank(message = "É obrigatório identificação do pet")
        Long petId,
        @NotBlank(message = "Precisa informar a categoria do serviço")
        String category,
        @NotBlank(message = "Precisa informar o nome do serviço")
        String nameService
) {
}
