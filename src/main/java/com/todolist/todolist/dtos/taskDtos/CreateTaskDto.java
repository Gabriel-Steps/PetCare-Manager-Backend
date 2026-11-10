package com.todolist.todolist.dtos.taskDtos;

import jakarta.validation.constraints.NotBlank;

public record CreateTaskDto(
        @NotBlank(message = "O campo título é obrigatório!")
        String title,
        @NotBlank(message = "O campo descrição é obrigatório!")
        String description
) {
}
