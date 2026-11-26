package com.project.petcaremanager.dtos.clientDtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateClientDto(
        @NotBlank(message = "O campo nome é obrigatório")
        String name,
        @NotBlank(message = "O campo CPF é obrigatório")
        String cpf,
        @NotBlank(message = "O campo telefone é obrigatório")
        String phone,
        String secondaryPhone,
        String email,
        @NotNull @Positive
        Long addressId,
        @NotNull
        boolean contactViaWhatsApp,
        String observation
) {
}
