package com.project.petcaremanager.dtos.clientDtos.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateClientDto(
        @NotBlank(message = "O campo nome é obrigatório")
        String name,
        @NotBlank(message = "O campo CPF é obrigatório")
        String cpf,
        @NotBlank(message = "O campo telefone é obrigatório")
        String phone,
        String secondaryPhone,
        String email,
        @NotBlank(message = "É obrigatório saber se o usuário aceita contato via Whats")
        boolean contactViaWhatsApp,
        String observation
) {
}
