package com.project.petcaremanager.dtos.unitDtos.response;

import com.project.petcaremanager.models.Address;

public record ResponseUnitDto(
        Long id,
        String name,
        String cnpj,
        String companyName,
        String phone,
        String email,
        Address address
) {
}
