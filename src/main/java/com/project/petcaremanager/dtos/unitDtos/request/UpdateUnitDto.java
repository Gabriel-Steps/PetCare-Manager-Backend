package com.project.petcaremanager.dtos.unitDtos.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateUnitDto(
        @NotBlank(message = "Informe o nome da sua unidade")
        String name,
        @NotBlank(message = "Iforme o CNPJ da sua unidade")
        String cnpj,
        String companyName,
        @NotBlank(message = "informe o telefone da sua unidade")
        String phone,
        String email
) {
}
