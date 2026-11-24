package com.project.petcaremanager.dtos.unitOperationDtos.response;

import com.project.petcaremanager.models.enums.DayOfWeek;

import java.util.TimeZone;

public record ResponseUnitOperationDto(
        Long unitId,
        DayOfWeek dayOfWeek,
        TimeZone startTime,
        TimeZone endTime
) {
}
