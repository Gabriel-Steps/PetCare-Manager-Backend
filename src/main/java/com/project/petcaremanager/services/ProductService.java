package com.project.petcaremanager.services;

import com.project.petcaremanager.dtos.productDtos.request.CreateProductDto;
import com.project.petcaremanager.dtos.productDtos.request.UpdateProductDto;
import com.project.petcaremanager.dtos.productDtos.request.UpdateProductStockQuantityDto;
import com.project.petcaremanager.dtos.productDtos.response.ResponseProductDto;
import com.project.petcaremanager.exceptions.ResourceNotFoundException;
import com.project.petcaremanager.models.Product;
import com.project.petcaremanager.models.Unit;
import com.project.petcaremanager.repositories.ProductRepository;
import com.project.petcaremanager.repositories.UnitRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final UnitRepository unitRepository;

    public ProductService(ProductRepository productRepository, UnitRepository unitRepository){
        this.productRepository = productRepository;
        this.unitRepository = unitRepository;
    }

    public void create(CreateProductDto dto){
        Unit unit = unitRepository.findById(dto.unitId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhuma unidade encontrada com o identificador " + dto.unitId()
                ));

        Product product = new Product(
                null,
                unit,
                dto.name(),
                dto.barCode(),
                dto.manufacturer(),
                dto.stockQuantity(),
                dto.unitPrice(),
                dto.imageUrl()
        );
        productRepository.save(product);
    }

    public Page<ResponseProductDto> findAllByUnitId(Long id){
        if(!unitRepository.existsById(id))
            throw new ResourceNotFoundException(
                    "Nenhuma unidade encontrada com esse identificador " + id
            );

        return productRepository.findAllByUnitId(id)
                .map(product -> new ResponseProductDto(
                        product.getId(),
                        id,
                        product.getName(),
                        product.getBarcode(),
                        product.getManufacturer(),
                        product.getStockQuantity(),
                        product.getUnitPrice()
                ));
    }

    public void update(UpdateProductDto dto, Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "nenhum produto encontrado com esse identificador " + id
                ));

        product.update(dto);
        productRepository.save(product);
    }

    public void delete(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "nenhum produto encontrado com esse identificador " + id
                ));

        productRepository.delete(product);
    }

    public void reporEstoque(Long id, UpdateProductStockQuantityDto dto){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "nenhum produto encontrado com esse identificador " + id
                ));

        product.depositar(dto.quantity());
        productRepository.save(product);
    }

    public void retirarEstoque(Long id, UpdateProductStockQuantityDto dto){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "nenhum produto encontrado com esse identificador " + id
                ));

        if(product.getStockQuantity() < dto.quantity())
            throw new ResourceNotFoundException(
                    "Não pode retirar quantidade do produto maior do que tem no estoque, identificador do produto " + id
            );

        product.retirar(dto.quantity());
        productRepository.save(product);
    }
}
