package com.project.petcaremanager.dtos.productDtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record UpdateProductDto(
        @NotBlank(message = "Informe o nome do produto")
        String name,
        @NotBlank(message = "Informe o código de barra do produto")
        String barCode,
        @NotBlank(message = "Informe o nome do fabricante")
        String manufacturer,
        @NotNull(message = "Informe a quantidade desse produto no estoque atual") @PositiveOrZero
        int stockQuantity,
        @NotNull(message = "Informe o preço por unidade do produto") @Positive
        BigDecimal unitPrice,
        @NotBlank(message = "Coloque uma imagem para o produto")
        String imageUrl
) {
}
