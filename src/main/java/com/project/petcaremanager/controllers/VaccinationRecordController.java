package com.project.petcaremanager.controllers;

import com.project.petcaremanager.dtos.ResponseApiDto;
import com.project.petcaremanager.dtos.vaccinationRecordDtos.request.CreateVaccinationRecordDto;
import com.project.petcaremanager.dtos.vaccinationRecordDtos.response.ResponseVaccinationRecordDto;
import com.project.petcaremanager.services.VaccinationRecordService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vaccination-record")
public class VaccinationRecordController {

    private final VaccinationRecordService vaccinationRecordService;

    public VaccinationRecordController(VaccinationRecordService vaccinationRecordService) {
        this.vaccinationRecordService = vaccinationRecordService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseApiDto<Void> create(
            @RequestBody @Valid CreateVaccinationRecordDto dto
            ){

        vaccinationRecordService.create(dto);

        return new ResponseApiDto<>(true, null, null);
    }

    @GetMapping("/pet/{id}")
    public ResponseEntity<ResponseApiDto<Page<ResponseVaccinationRecordDto>>> findAllByPetId(
            @PathVariable Long id,
            Pageable pageable
    ){
        return ResponseEntity.ok(new ResponseApiDto<>(true, null, vaccinationRecordService.findAllByPetId(id, pageable)));
    }
}
