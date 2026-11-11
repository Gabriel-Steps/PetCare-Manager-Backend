package com.project.petcaremanager.models;

import com.project.petcaremanager.models.enums.ServiceCategory;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ServiceCategory category;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private int duration;

    @Column(nullable = false)
    private BigDecimal basePrice;

    @Column(nullable = false)
    private boolean requiresVeterinarian;

    @Column(nullable = false)
    private boolean isActive;

    public Service() { }

    public Service(
            Long id,
            String name,
            ServiceCategory category,
            int duration,
            BigDecimal basePrice,
            boolean requiresVeterinarian) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.duration = duration;
        this.basePrice = basePrice;
        this.requiresVeterinarian = requiresVeterinarian;
        this.isActive = true;
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

    public ServiceCategory getCategory() {
        return category;
    }

    public void setCategory(ServiceCategory category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public boolean isRequiresVeterinarian() {
        return requiresVeterinarian;
    }

    public void setRequiresVeterinarian(boolean requiresVeterinarian) {
        this.requiresVeterinarian = requiresVeterinarian;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
