package com.project.petcaremanager.services;

import com.project.petcaremanager.dtos.stockMovementDtos.request.CreateStockMovementDto;
import com.project.petcaremanager.dtos.stockMovementDtos.response.ResponseStockMovementDto;
import com.project.petcaremanager.exceptions.ResourceNotFoundException;
import com.project.petcaremanager.models.Product;
import com.project.petcaremanager.models.StockMovement;
import com.project.petcaremanager.models.enums.MovementType;
import com.project.petcaremanager.repositories.ProductRepository;
import com.project.petcaremanager.repositories.StockMovementRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StockMovementService {

    private final StockMovementRepository stockMovementRepository;
    private final ProductRepository productRepository;

    public StockMovementService(StockMovementRepository stockMovementRepository, ProductRepository productRepository) {
        this.stockMovementRepository = stockMovementRepository;
        this.productRepository = productRepository;
    }

    public void create(CreateStockMovementDto dto){
        Product product = productRepository.findById(dto.productId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhium produto encontrado com o identifcador " + dto.productId()
                ));

        MovementType movementType;

        try{
            movementType = MovementType.valueOf(dto.type().toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Tipo de movimentação inválido ", e);
        }

        StockMovement stockMovement = new StockMovement(
                null,
                product,
                movementType,
                dto.quantity()
        );

        stockMovementRepository.save(stockMovement);
    }

    public Page<ResponseStockMovementDto> findAllProductId(Long id, Pageable pageable){
        return stockMovementRepository.findAllByProductId(id, pageable)
                .map(stockMovement -> new ResponseStockMovementDto(
                        stockMovement.getProduct().getId(),
                        stockMovement.getProduct().getName(),
                        stockMovement.getDayMovement(),
                        stockMovement.getQuantity(),
                        stockMovement.getType()
                ));
    }

public void deleteAllByProductId(Long id){
        stockMovementRepository.deleteAllByProductId(id);
}
}
