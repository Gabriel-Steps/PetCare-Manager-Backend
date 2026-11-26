package com.project.petcaremanager.models;

import com.project.petcaremanager.dtos.productDtos.request.UpdateProductDto;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String barcode;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private int stockQuantity;

    @Column(nullable = false)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private String imageUrl;

    public Product() { }

    public Product(
            Long id,
            Unit unit,
            String name,
            String barcode,
            String manufacturer,
            int stockQuantity,
            BigDecimal unitPrice,
            String imageUrl) {
        this.id = id;
        this.unit = unit;
        this.name = name;
        this.barcode = barcode;
        this.manufacturer = manufacturer;
        this.stockQuantity = stockQuantity;
        this.unitPrice = unitPrice;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void update(UpdateProductDto dto){
        this.name = dto.name();
        this.barcode = dto.barCode();
        this.manufacturer = dto.manufacturer();
        this.stockQuantity = dto.stockQuantity();
        this.unitPrice = dto.unitPrice();
        this.imageUrl = dto.imageUrl();
    }

    public void depositar(int quantity){
        this.stockQuantity += quantity;
    }

    public void retirar(int quantity){
        this.stockQuantity -= quantity;
    }
}
