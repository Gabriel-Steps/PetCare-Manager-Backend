package com.project.petcaremanager.dtos.clientDtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateClientDto(
        @NotBlank(message = "O campo nome é obrigatório")
        String name,
        @NotBlank(message = "O campo CPF é obrigatório")
        String cpf,
        @NotBlank(message = "O campo telefone é obrigatório")
        String phone,
        String secondaryPhone,
        String email,
        @NotNull
        boolean contactViaWhatsApp,
        String observation
) {
}
