package com.project.petcaremanager.controllers;

import com.project.petcaremanager.dtos.ResponseApiDto;
import com.project.petcaremanager.dtos.unitDtos.request.CreateUnitDto;
import com.project.petcaremanager.dtos.unitDtos.request.UpdateUnitDto;
import com.project.petcaremanager.dtos.unitDtos.response.ResponseUnitDto;
import com.project.petcaremanager.services.UnitService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/unit")
public class UnitController {

    private final UnitService unitService;;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseApiDto<Void> create(
            @RequestBody @Valid CreateUnitDto dto
            ){

        unitService.create(dto);

        return new ResponseApiDto<>(true, "Unidade criada com sucesso!", null);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ResponseApiDto<Page<ResponseUnitDto>>> findAllByUserId(
            @PathVariable Long id,
            Pageable pageable
    ){
        return ResponseEntity.ok(new ResponseApiDto<>(true, null, unitService.findAllByUserId(id, pageable)));
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<ResponseApiDto<Void>> updateActiveStatus(
            @PathVariable Long id
    ){
        unitService.updateActiveStatus(id);

        return ResponseEntity.ok(new ResponseApiDto<>(true, null, null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseApiDto<Void>> update(
            @PathVariable Long id,
            UpdateUnitDto dto
    ){
        unitService.update(id, dto);

        return ResponseEntity.ok(new ResponseApiDto<>(true, "Unidade atualizada com sucesso!", null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseApiDto<ResponseUnitDto>> findById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(new ResponseApiDto<>(true, null, unitService.findById(id)));
    }
}
