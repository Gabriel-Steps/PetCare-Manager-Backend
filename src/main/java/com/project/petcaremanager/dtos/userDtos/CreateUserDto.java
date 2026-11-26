package com.project.petcaremanager.dtos.userDtos;

import jakarta.validation.constraints.NotBlank;

public record CreateUserDto (
        @NotBlank(message = "Informe o nome do usuário")
        String name,
        @NotBlank(message = "Informe o e-mail do usuário")
        String email,
        @NotBlank(message = "Informe a senha do usuário")
        String password,
        @NotBlank(message = "Informe o papel do usuário no sistema")
        String role
){
}
