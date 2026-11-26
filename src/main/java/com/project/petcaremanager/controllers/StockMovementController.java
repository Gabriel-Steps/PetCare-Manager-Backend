package com.project.petcaremanager.controllers;

import com.project.petcaremanager.dtos.ResponseApiDto;
import com.project.petcaremanager.dtos.stockMovementDtos.request.CreateStockMovementDto;
import com.project.petcaremanager.dtos.stockMovementDtos.response.ResponseStockMovementDto;
import com.project.petcaremanager.services.StockMovementService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stock-movement")
public class StockMovementController {

    private final StockMovementService stockMovementService;

    public StockMovementController(StockMovementService stockMovementService) {
        this.stockMovementService = stockMovementService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseApiDto<Void> create(
            @RequestBody @Valid CreateStockMovementDto dto
    ){

        stockMovementService.create(dto);

        return new ResponseApiDto<>(true, null, null);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ResponseApiDto<Page<ResponseStockMovementDto>>> findAllByProductId(
            @PathVariable Long id,
            Pageable pageable
    ){
        return ResponseEntity.ok(new ResponseApiDto<>(true, null, stockMovementService.findAllProductId(id, pageable)));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<ResponseApiDto<Void>> deleteAllByProductId(
            @PathVariable Long id
    ){
        stockMovementService.deleteAllByProductId(id);

        return ResponseEntity.ok(new ResponseApiDto<>(true, null, null));
    }
}
