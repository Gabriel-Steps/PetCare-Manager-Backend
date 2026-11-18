package com.project.petcaremanager.models;

import com.project.petcaremanager.models.enums.MovementType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "stock_movements")
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MovementType type;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private LocalDateTime dayMovement;

    public StockMovement(
            Long id,
            Product product,
            MovementType type,
            int quantity,
            LocalDateTime dayMovement) {
        this.id = id;
        this.product = product;
        this.type = type;
        this.quantity = quantity;
        this.dayMovement = dayMovement;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public MovementType getType() {
        return type;
    }

    public void setType(MovementType type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getDayMovement() {
        return dayMovement;
    }

    public void setDayMovement(LocalDateTime dayMovement) {
        this.dayMovement = dayMovement;
    }
}
