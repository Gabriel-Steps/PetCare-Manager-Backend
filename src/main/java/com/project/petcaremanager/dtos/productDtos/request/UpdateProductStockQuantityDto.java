package com.project.petcaremanager.dtos.productDtos.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateProductStockQuantityDto(
        @NotBlank(message = "Informe a quantidade do produto que deseja mover")
        int quantity
) {
}
