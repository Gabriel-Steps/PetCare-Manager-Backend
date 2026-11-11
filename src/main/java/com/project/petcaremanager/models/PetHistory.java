package com.project.petcaremanager.models;

import com.project.petcaremanager.models.enums.ServiceCategory;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "pet_histories")
public class PetHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ServiceCategory category;

    @Column(nullable = false)
    private String nameService;

    @Column(nullable = false)
    private LocalDate serviceDay;

    public PetHistory() { }

    public PetHistory(
            Long id,
            Pet pet,
            ServiceCategory category,
            String nameService
    ){
        this.id = id;
        this.pet = pet;
        this.category = category;
        this.nameService = nameService;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public ServiceCategory getCategory() {
        return category;
    }

    public void setCategory(ServiceCategory category) {
        this.category = category;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public LocalDate getServiceDay() {
        return serviceDay;
    }

    public void setServiceDay(LocalDate serviceDay) {
        this.serviceDay = serviceDay;
    }
}