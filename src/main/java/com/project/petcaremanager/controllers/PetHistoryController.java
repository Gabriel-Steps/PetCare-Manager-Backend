package com.project.petcaremanager.controllers;

import com.project.petcaremanager.dtos.ResponseApiDto;
import com.project.petcaremanager.dtos.petHistory.request.CreatePetHistoryDto;
import com.project.petcaremanager.dtos.petHistory.response.ResponsePetHistoryDto;
import com.project.petcaremanager.services.PetHistoryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/petHistory")
public class PetHistoryController {

    private final PetHistoryService petHistoryService;

    public PetHistoryController(PetHistoryService petHistoryService){
        this.petHistoryService = petHistoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseApiDto<Void> create(
            @RequestBody @Valid CreatePetHistoryDto dto
            ){
        petHistoryService.create(dto);

        return new ResponseApiDto<>(true, null, null);
    }

    @GetMapping("{id}/pet")
    public ResponseEntity<ResponseApiDto<Page<ResponsePetHistoryDto>>> findAllByPetId(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(new ResponseApiDto<>(true, null, petHistoryService.findAllByPetId(id)));
    }
}
