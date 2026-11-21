package com.project.petcaremanager.dtos.stockMovementDtos.response;

import com.project.petcaremanager.models.enums.MovementType;

import java.time.LocalDateTime;

public record ResponseStockMovementDto(
        Long productId,
        String productName,
        LocalDateTime dayMovement,
        int quantity,
        MovementType movementType
) {
}
