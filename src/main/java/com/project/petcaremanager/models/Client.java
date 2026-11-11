package com.project.petcaremanager.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = true)
    private String secondaryPhone;

    @Column(nullable = true)
    private String email;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(nullable = false)
    private boolean contactViaWhatsApp;

    @Column(nullable = true)
    private String observation;

    @Column(nullable = false)
    private boolean isActive;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Client() { }

    public Client(
            Long id,
            String name,
            String cpf,
            String phone,
            Address address,
            boolean contactViaWhatsApp) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.address = address;
        this.contactViaWhatsApp = contactViaWhatsApp;
        this.isActive = true;
        this.createdAt = LocalDateTime.now();

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSecondaryPhone() {
        return secondaryPhone;
    }

    public void setSecondaryPhone(String secondaryPhone) {
        this.secondaryPhone = secondaryPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isContactViaWhatsApp() {
        return contactViaWhatsApp;
    }

    public void setContactViaWhatsApp(boolean contactViaWhatsApp) {
        this.contactViaWhatsApp = contactViaWhatsApp;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    private void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
