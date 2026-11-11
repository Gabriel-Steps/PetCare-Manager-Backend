package com.project.petcaremanager.models;

import com.project.petcaremanager.models.enums.SchedulingStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "schedulings")
public class Scheduling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "professional_id")
    private Professional professional;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @ManyToOne
    @JoinColumn(name = "reception_id")
    private User reception;

    @Column(nullable = false)
    private LocalDateTime startDateTime;

    @Column(nullable = false)
    private LocalDateTime endDateTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SchedulingStatus status;

    @Column(nullable = false)
    private BigDecimal finalPrice;

    @Column(nullable = false)
    private String observation;

    @Column(nullable = false)
    private LocalDateTime lastUpdate;

    @Column(nullable = true)
    private String reasonCancellation;

    public Scheduling() { }

    public Scheduling(
            Long id,
            Pet pet,
            Professional professional,
            Unit unit, User reception,
            LocalDateTime startDateTime,
            LocalDateTime endDateTime,
            SchedulingStatus status,
            BigDecimal finalPrice,
            String observation,
            LocalDateTime lastUpdate) {
        this.id = id;
        this.pet = pet;
        this.professional = professional;
        this.unit = unit;
        this.reception = reception;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.status = status;
        this.finalPrice = finalPrice;
        this.observation = observation;
        this.lastUpdate = lastUpdate;
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

    public Professional getProfessional() {
        return professional;
    }

    public void setProfessional(Professional professional) {
        this.professional = professional;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public User getReception() {
        return reception;
    }

    public void setReception(User reception) {
        this.reception = reception;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public SchedulingStatus getStatus() {
        return status;
    }

    public void setStatus(SchedulingStatus status) {
        this.status = status;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getReasonCancellation() {
        return reasonCancellation;
    }

    public void setReasonCancellation(String reasonCancellation) {
        this.reasonCancellation = reasonCancellation;
    }
}
