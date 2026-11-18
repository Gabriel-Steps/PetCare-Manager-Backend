package com.project.petcaremanager.dtos.productDtos.request;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record UpdateProductDto(
        @NotBlank(message = "Informe o nome do produto")
        String name,
        @NotBlank(message = "Informe o código de barra do produto")
        String barCode,
        @NotBlank(message = "Informe o nome do fabricante")
        String manufacturer,
        @NotBlank(message = "Informe a quantidade desse produto no estoque atual")
        int stockQuantity,
        @NotBlank(message = "Informe o preço por unidade do produto")
        BigDecimal unitPrice,
        @NotBlank(message = "Coloque uma imagem para o produto")
        String imageUrl
) {
}
