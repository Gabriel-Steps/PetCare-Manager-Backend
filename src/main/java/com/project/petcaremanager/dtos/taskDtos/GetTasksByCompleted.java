package com.project.petcaremanager.dtos.taskDtos;

import jakarta.validation.constraints.NotBlank;

public record GetTasksByCompleted(
        @NotBlank
        boolean status
) {
}
