package com.project.petcaremanager.dtos.professionalDtos.response;

public record ResponseProfessionalDto(
        Long id,
        Long userId,
        String nameUser,
        String emailUser,
        String nameUnit
) {
}
