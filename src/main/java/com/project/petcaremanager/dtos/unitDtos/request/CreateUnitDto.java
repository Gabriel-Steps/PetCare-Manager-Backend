package com.project.petcaremanager.dtos.unitDtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

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
        @NotNull(message = "Informe o endereço da sua unidade") @Positive
        Long addressId,
        @NotNull(message = "Informe o usuário que criou a unidade") @Positive
        Long userId
) {
}
