package com.project.petcaremanager.dtos.stockMovementDtos.request;

import jakarta.validation.constraints.NotBlank;

public record CreateStockMovementDto(
        @NotBlank(message = "Informe o produto da movimentação")
        Long productId,
        @NotBlank(message = "Informe o tipo da movimentação")
        String type,
        @NotBlank(message = "Informe a quantidade do produto da movimentação")
        int quantity
) {
}
