package com.project.petcaremanager.models;

import jakarta.persistence.*;

@Entity
@Table(name = "professionals")
public class Professional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String specialty;

    @Column(nullable = false)
    private String crmv;

    @Column(nullable = false)
    private String phone;

    public Professional() { }

    public Professional(Long id, String specialty, String crmv, String phone){
        this.setId(id);
        this.setSpecialty(specialty);
        this.setCrmv(crmv);
        this.setPhone(phone);
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getCrmv() {
        return crmv;
    }

    public void setCrmv(String crmv) {
        this.crmv = crmv;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
