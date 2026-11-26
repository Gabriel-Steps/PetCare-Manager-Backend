package com.project.petcaremanager.dtos.unitOperationDtos.response;

import com.project.petcaremanager.models.enums.DayOfWeek;

import java.time.LocalTime;

public record ResponseUnitOperationDto(
        Long unitId,
        DayOfWeek dayOfWeek,
        LocalTime startTime,
        LocalTime endTime
) {
}
