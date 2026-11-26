package com.project.petcaremanager.dtos.productDtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateProductStockQuantityDto(
        @NotNull(message = "Informe a quantidade do produto que deseja mover") @Positive
        int quantity
) {
}
