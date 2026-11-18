package com.project.petcaremanager.dtos.clientDtos.response;

import com.project.petcaremanager.models.Address;

import java.time.LocalDateTime;

public record ResponseClientDto(Long id,
                                String name,
                                String cpf,
                                String email,
                                String phone,
                                String secondaryPhone,
                                Address address,
                                boolean contactViaWhatsApp,
                                String observation,
                                boolean isActive,
                                LocalDateTime createdAt) {
}
