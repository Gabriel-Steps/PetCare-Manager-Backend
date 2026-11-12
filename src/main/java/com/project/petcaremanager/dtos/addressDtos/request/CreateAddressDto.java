package com.project.petcaremanager.dtos.addressDtos.request;

import jakarta.validation.constraints.NotBlank;

public record CreateAddressDto(
        @NotBlank(message = "O campo rua é obrigatório")
        String street,
        @NotBlank(message = "O campo número é obrigatório")
        int number,
        String supplement,
        @NotBlank(message = "O campo bairro é obrigatório")
        String neighborhood,
        @NotBlank(message = "O campo cidade é obrigatório")
        String city,
        @NotBlank(message = "O campo estado é obrigatório")
        String uf
) {
}
