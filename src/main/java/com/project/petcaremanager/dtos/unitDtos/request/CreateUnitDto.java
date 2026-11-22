package com.project.petcaremanager.dtos.unitDtos.request;

import jakarta.validation.constraints.NotBlank;

import javax.sound.midi.MetaMessage;

public record CreateUnitDto(
        @NotBlank(message = "Informe o nome da sua unidade")
        String name,
        @NotBlank(message = "Iforme o CNPJ da sua unidade")
        String cnpj,
        String companyName,
        @NotBlank(message = "informe o telefone da sua unidade")
        String phone,
        String email,
        @NotBlank(message = "Informe o endereço da sua unidade")
        Long addressId,
        @NotBlank(message = "Informe o usuário que criou a unidade")
        Long userId
) {
}
