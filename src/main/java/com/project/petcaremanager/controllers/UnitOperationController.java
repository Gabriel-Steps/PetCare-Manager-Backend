package com.project.petcaremanager.controllers;

import com.project.petcaremanager.dtos.ResponseApiDto;
import com.project.petcaremanager.dtos.unitOperationDtos.request.CreateUnitOperationsDto;
import com.project.petcaremanager.dtos.unitOperationDtos.request.UpdateUnitOperationDto;
import com.project.petcaremanager.dtos.unitOperationDtos.response.ResponseUnitOperationDto;
import com.project.petcaremanager.services.UnitOperationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/unit-operation")
public class UnitOperationController {

    private final UnitOperationService unitOperationService;

    public UnitOperationController(UnitOperationService unitOperationService) {
        this.unitOperationService = unitOperationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseApiDto<Void> create(
            @RequestBody @Valid List<CreateUnitOperationsDto> dtoList
            ){

        unitOperationService.create(dtoList);

        return new ResponseApiDto<>(true, null, null);
    }

    @GetMapping("/unit/{id}")
    public ResponseEntity<ResponseApiDto<List<ResponseUnitOperationDto>>> findAllByUnitId(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(new ResponseApiDto<>(true, null, unitOperationService.findAllByUnitId(id)));
    }

    @PutMapping("/unit/{id}")
    public ResponseEntity<ResponseApiDto<Void>> update(
            @PathVariable Long id,
            @RequestBody @Valid List<UpdateUnitOperationDto> dtoList
    ){
        unitOperationService.updateAllByUnitId(id, dtoList);

        return ResponseEntity.ok(new ResponseApiDto<>(true, null, null));
    }
}
