package com.project.petcaremanager.dtos.addressDtos.response;

public record ResponseAddressDto(Long id, String street, int number, String supplement, String neighborhood, String city, String uf) {
}
