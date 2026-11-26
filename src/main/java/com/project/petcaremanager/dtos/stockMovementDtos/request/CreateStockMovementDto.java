package com.project.petcaremanager.dtos.stockMovementDtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateStockMovementDto(
        @NotNull(message = "Informe o produto da movimentação") @Positive
        Long productId,
        @NotBlank(message = "Informe o tipo da movimentação")
        String type,
        @NotNull(message = "Informe a quantidade do produto da movimentação") @Positive
        int quantity
) {
}
