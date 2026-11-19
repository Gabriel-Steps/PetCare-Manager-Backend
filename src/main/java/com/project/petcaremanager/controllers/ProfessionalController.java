package com.project.petcaremanager.controllers;

import com.project.petcaremanager.dtos.ResponseApiDto;
import com.project.petcaremanager.dtos.professionalDtos.request.CreateProfessionalDto;
import com.project.petcaremanager.dtos.professionalDtos.request.UpdateProfessionalDto;
import com.project.petcaremanager.dtos.professionalDtos.response.ResponseProfessionalDto;
import com.project.petcaremanager.services.ProfessionalService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/professional")
public class ProfessionalController {

    private final ProfessionalService professionalService;

    public ProfessionalController(ProfessionalService professionalService){
        this.professionalService = professionalService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseApiDto<Void> create(
            @RequestBody @Valid CreateProfessionalDto dto
            ){

        professionalService.create(dto);

        return new ResponseApiDto<>(true, null, null);
    }

    @GetMapping("/{id}/unit")
    public ResponseEntity<ResponseApiDto<Page<ResponseProfessionalDto>>> findAllByUnitId(
            @PathVariable Long id,
            Pageable pageable
    ){
        return ResponseEntity.ok(new ResponseApiDto<>(true, null, professionalService.findAllByUnitId(id, pageable)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseApiDto<Void>> update(
            @PathVariable Long id,
            @RequestBody @Valid UpdateProfessionalDto dto
            ){

        professionalService.update(id, dto);

        return ResponseEntity.ok(new ResponseApiDto<>(true, "Profissinal atualizado com sucesso", null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApiDto<Void>> delete(
            @PathVariable Long id
    ){

        professionalService.delete(id);

        return ResponseEntity.ok(new ResponseApiDto<>(true, "Profissional removido com sucesso", null));
    }
}
