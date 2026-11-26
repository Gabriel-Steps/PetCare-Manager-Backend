package com.project.petcaremanager.dtos.addressDtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record CreateAddressDto(
        @NotBlank(message = "O campo rua é obrigatório")
        String street,
        @NotNull @PositiveOrZero
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
