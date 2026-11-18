package com.project.petcaremanager.controllers;

import com.project.petcaremanager.dtos.ResponseApiDto;
import com.project.petcaremanager.dtos.petDtos.request.CreatePetDto;
import com.project.petcaremanager.dtos.petDtos.request.UpdatePetDto;
import com.project.petcaremanager.dtos.petDtos.response.ResponsePetDto;
import com.project.petcaremanager.services.PetService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pet")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService){
        this.petService = petService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseApiDto<Void> create(
            @RequestBody @Valid CreatePetDto dto
            ){

        petService.create(dto);

        return new ResponseApiDto<>(true, "Pet cadastrado com sucesso", null);
    }

    @GetMapping("/{id}/client")
    public ResponseEntity<ResponseApiDto<Page<ResponsePetDto>>> findAllByClient(
            @PathVariable Long id,
            Pageable pageable
    ){
        return ResponseEntity.ok(new ResponseApiDto<>(true, null, petService.findAllByClientId(id, pageable)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseApiDto<Void>> update(
            @RequestBody @Valid UpdatePetDto dto,
            @PathVariable Long id
            ){

        petService.update(dto, id);

        return ResponseEntity.ok(new ResponseApiDto<>(true, "Pet atualizado com sucesso!", null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApiDto<Void>> delete(
            @PathVariable Long id
    ){

        petService.delete(id);

        return ResponseEntity.ok(new ResponseApiDto<>(true, "Pet removido com sucesso!", null));
    }
}
