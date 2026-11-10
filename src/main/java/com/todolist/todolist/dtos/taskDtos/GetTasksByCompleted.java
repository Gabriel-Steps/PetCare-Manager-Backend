package com.todolist.todolist.dtos.taskDtos;

import jakarta.validation.constraints.NotBlank;

public record GetTasksByCompleted(
        @NotBlank
        boolean status
) {
}
