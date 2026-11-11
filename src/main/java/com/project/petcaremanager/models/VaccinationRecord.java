package com.project.petcaremanager.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "vaccination_record")
public class VaccinationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String coveredConditions;

    @Column(nullable = false)
    private LocalDate vaccinationDay;

    @Column(nullable = false)
    private String lotNumber;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private LocalDate boosterVaccine;

    public VaccinationRecord(
            Long id,
            String name,
            Pet pet,
            String coveredConditions,
            LocalDate vaccinationDay,
            String lotNumber,
            String manufacturer,
            LocalDate boosterVaccine) {
        this.id = id;
        this.name = name;
        this.pet = pet;
        this.coveredConditions = coveredConditions;
        this.vaccinationDay = vaccinationDay;
        this.lotNumber = lotNumber;
        this.manufacturer = manufacturer;
        this.boosterVaccine = boosterVaccine;
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

    public String getCoveredConditions() {
        return coveredConditions;
    }

    public void setCoveredConditions(String coveredConditions) {
        this.coveredConditions = coveredConditions;
    }

    public LocalDate getVaccinationDay() {
        return vaccinationDay;
    }

    public void setVaccinationDay(LocalDate vaccinationDay) {
        this.vaccinationDay = vaccinationDay;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public LocalDate getBoosterVaccine() {
        return boosterVaccine;
    }

    public void setBoosterVaccine(LocalDate boosterVaccine) {
        this.boosterVaccine = boosterVaccine;
    }
}
