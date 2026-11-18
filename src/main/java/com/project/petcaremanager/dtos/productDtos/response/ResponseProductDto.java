package com.project.petcaremanager.dtos.productDtos.response;

import java.math.BigDecimal;

public record ResponseProductDto(
        Long id,
        Long unitId,
        String name,
        String barcode,
        String manufacturer,
        int stockQuantity,
        BigDecimal unitPrice
) {
}
