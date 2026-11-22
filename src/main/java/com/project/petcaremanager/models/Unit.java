package com.project.petcaremanager.models;

import com.project.petcaremanager.dtos.unitDtos.request.UpdateUnitDto;
import jakarta.persistence.*;

@Entity
@Table(name = "units")
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String cnpj;

    @Column(nullable = true)
    private String companyName;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = true)
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private boolean isActive;

    public Unit() { }

    public Unit(
            Long id,
            String name,
            String cnpj,
            String companyName,
            String phone,
            String email,
            Address address,
            User user) {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
        this.companyName = companyName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.user = user;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void update(UpdateUnitDto dto){
        this.name = dto.name();
        this.cnpj = dto.cnpj();
        this.companyName = dto.companyName();
        this.phone = dto.phone();
        this.email = dto.email();
    }

    public void changeStatus(){
        this.isActive = !this.isActive;
    }
}
