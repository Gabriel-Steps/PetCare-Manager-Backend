package com.project.petcaremanager.dtos.userDtos;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserDto(
        @NotBlank(message = "O nome é obrigatório!")
        String name,
        @NotBlank(message = "O e-mail é obrgatório!")
        String email
) {
}
