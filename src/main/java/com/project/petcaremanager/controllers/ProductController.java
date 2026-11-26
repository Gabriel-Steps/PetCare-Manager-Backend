package com.project.petcaremanager.controllers;

import com.project.petcaremanager.dtos.ResponseApiDto;
import com.project.petcaremanager.dtos.productDtos.request.CreateProductDto;
import com.project.petcaremanager.dtos.productDtos.request.UpdateProductDto;
import com.project.petcaremanager.dtos.productDtos.request.UpdateProductStockQuantityDto;
import com.project.petcaremanager.dtos.productDtos.response.ResponseProductDto;
import com.project.petcaremanager.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseApiDto<Void> create(
            @RequestBody @Valid CreateProductDto dto
            ){

        productService.create(dto);

        return new ResponseApiDto<>(true, "Produto cadastrado com sucesso", null);
    }

    @GetMapping("/{id}/unit")
    public ResponseEntity<ResponseApiDto<Page<ResponseProductDto>>> findAllByUnitId(
        @PathVariable Long id,
        Pageable pageable
    ){
        return ResponseEntity.ok(new ResponseApiDto<>(true, null, productService.findAllByUnitId(id, pageable)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseApiDto<Void>> update(
            @PathVariable Long id,
            @RequestBody @Valid UpdateProductDto dto
            ){

        productService.update(dto, id);

        return ResponseEntity.ok(new ResponseApiDto<>(true, "Produto realizado com sucesso", null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApiDto<Void>> delete(
            @PathVariable Long id
    ){
        productService.delete(id);

        return ResponseEntity.ok(new ResponseApiDto<>(true, "Produto deletado com sucesso", null));
    }

    @PutMapping("/{id}/depositar")
    public ResponseEntity<ResponseApiDto<Void>> deposit(
            @PathVariable Long id,
            @RequestBody @Valid UpdateProductStockQuantityDto dto
            ){
        productService.reporEstoque(id, dto);

        return ResponseEntity.ok(new ResponseApiDto<>(true, "Reposição do produto feito", null));
    }

    @PutMapping("/{id}/retirar")
    public ResponseEntity<ResponseApiDto<Void>> retiree(
            @PathVariable Long id,
            @RequestBody @Valid UpdateProductStockQuantityDto dto
    ){
        productService.retirarEstoque(id, dto);

        return ResponseEntity.ok(new ResponseApiDto<>(true, "Retirada dos produtos realiazo com sucesso", null));
    }
}
