package com.project.petcaremanager.dtos.schedulingDtos.models;

import com.project.petcaremanager.models.Pet;
import com.project.petcaremanager.models.Professional;
import com.project.petcaremanager.models.Unit;

public record ModelSchedulingEntitiesDto(
        Pet pet,
        Professional professional,
        Professional receptionist,
        Unit unit
) {
}
