package com.todolist.todolist.dtos.userDtos;

import jakarta.validation.constraints.NotBlank;

public record LoginUserDto(
        @NotBlank(message = "O campo e-mail é obrigatório!")
        String email,
        @NotBlank(message = "O campo seha é obrigatório!")
        String password
) {
}
