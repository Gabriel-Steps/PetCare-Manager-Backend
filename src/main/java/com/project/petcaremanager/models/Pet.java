package com.project.petcaremanager.models;

import com.project.petcaremanager.dtos.petDtos.request.UpdatePetDto;
import com.project.petcaremanager.models.enums.PetSize;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String species;

    @Column(nullable = true)
    private String race;

    @Column(nullable = false)
    private String sex;

    @Column(nullable = false)
    private boolean neutered;

    @Column(nullable = true)
    private LocalDate dateOfBirth;

    @Column(nullable = true)
    private BigDecimal weight;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PetSize size;

    @Column(nullable = true)
    private String color;

    @Column(nullable = true)
    private String allergy;

    @Column(nullable = true)
    private String temperament;

    @Column(nullable = true)
    private Long numberMicroship;

    // Talvez futuramente serviço para hospedar foto do pet

    @Column(nullable = false)
    private boolean isActive;

    public Pet() { }

    public Pet(
            Long id,
            Client client,
            String name,
            String species,
            String race,
            String sex,
            boolean neutered,
            LocalDate dateOfBirth,
            BigDecimal weight,
            PetSize size,
            String color,
            String allergy,
            String temperament,
            Long numberMicroship) {
        this.id = id;
        this.client = client;
        this.name = name;
        this.species = species;
        this.race = race;
        this.sex = sex;
        this.neutered = neutered;
        this.dateOfBirth = dateOfBirth;
        this.weight = weight;
        this.size = size;
        this.color = color;
        this.allergy = allergy;
        this.temperament = temperament;
        this.numberMicroship = numberMicroship;
        this.isActive = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public boolean isNeutered() {
        return neutered;
    }

    public void setNeutered(boolean neutered) {
        this.neutered = neutered;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public PetSize getSize() {
        return size;
    }

    public void setSize(PetSize size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public Long getNumberMicroship() {
        return numberMicroship;
    }

    public void setNumberMicroship(Long numberMicroship) {
        this.numberMicroship = numberMicroship;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void update(UpdatePetDto dto, PetSize petSize){
        this.name = dto.name();
        this.species = dto.species();
        this.race = dto.race();
        this.sex = dto.sex();
        this.neutered = dto.neutered();
        this.dateOfBirth = dto.dateOfBirth();
        this.weight = dto.weight();
        this.size = petSize;
        this.color = dto.color();
        this.allergy = dto.allergy();
        this.temperament = dto.temperament();
        this.numberMicroship = dto.numberMicroship();
    }
}
