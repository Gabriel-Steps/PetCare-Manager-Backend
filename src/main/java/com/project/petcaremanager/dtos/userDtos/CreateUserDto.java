package com.project.petcaremanager.dtos.userDtos;

import jakarta.validation.constraints.NotBlank;

public record CreateUserDto (
        @NotBlank(message =  "O campo nome é obrigatório!")
        String name,
        @NotBlank(message = "O campo e-mail é obrigatório!")
        String email,
        @NotBlank(message = "O campo senha é obrigatório!")
        String password,
        @NotBlank(message = "Informe a função do usuário")
        String role
){
}
