package com.project.petcaremanager.dtos.petHistory.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record CreatePetHistoryDto(
        @NotNull(message = "Informe o pet desse histórico") @Positive
        Long petId,
        @NotBlank(message = "Precisa informar a categoria do serviço")
        String category,
        @NotBlank(message = "Precisa informar o nome do serviço")
        String nameService
) {
}
