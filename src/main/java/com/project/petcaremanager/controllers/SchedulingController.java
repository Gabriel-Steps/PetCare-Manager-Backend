package com.project.petcaremanager.controllers;

import com.project.petcaremanager.dtos.ResponseApiDto;
import com.project.petcaremanager.dtos.schedulingDtos.request.CreateSchedulingDto;
import com.project.petcaremanager.dtos.schedulingDtos.request.UpdateSchedulingDto;
import com.project.petcaremanager.dtos.schedulingDtos.request.UpdateStatusSchedulingDto;
import com.project.petcaremanager.dtos.schedulingDtos.response.ResponseSchedulingDto;
import com.project.petcaremanager.services.SchedulingServices;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/scheduling")
public class SchedulingController {

    private final SchedulingServices schedulingServices;

    public SchedulingController(SchedulingServices schedulingServices) {
        this.schedulingServices = schedulingServices;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseApiDto<Void> create(
            @RequestBody @Valid CreateSchedulingDto dto
            ){

        schedulingServices.create(dto);

        return new ResponseApiDto<>(true, "Agendamento realizado com sucesso", null);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<ResponseApiDto<Page<ResponseSchedulingDto>>> findAllByClientId(
            @PathVariable Long id,
            Pageable pageable
    ){
        return ResponseEntity.ok(new ResponseApiDto<>(true, null, schedulingServices.findAllByClientId(id, pageable)));
    }

    @GetMapping("/pet/{id}")
    public ResponseEntity<ResponseApiDto<Page<ResponseSchedulingDto>>> findAllByPetId(
            @PathVariable Long id,
            Pageable pageable
    ){
        return ResponseEntity.ok(new ResponseApiDto<>(true, null, schedulingServices.findAllByPetId(id, pageable)));
    }

    @GetMapping("/unit/{id}")
    public ResponseEntity<ResponseApiDto<Page<ResponseSchedulingDto>>> findAllByUnitId(
            @PathVariable Long id,
            Pageable pageable
    ){
        return ResponseEntity.ok(new ResponseApiDto<>(true, null, schedulingServices.findAllByUnitId(id, pageable)));
    }

    @GetMapping("/professional/{id}")
    public ResponseEntity<ResponseApiDto<Page<ResponseSchedulingDto>>> findAllByProfessionalId(
            @PathVariable Long id,
            Pageable pageable
    ){
        return ResponseEntity.ok(new ResponseApiDto<>(true, null, schedulingServices.findAllByProfessionalId(id, pageable)));
    }

    @GetMapping("/receptionist/{id}")
    public ResponseEntity<ResponseApiDto<Page<ResponseSchedulingDto>>> findAllByReceptionistId(
            @PathVariable Long id,
            Pageable pageable
    ){
        return ResponseEntity.ok(new ResponseApiDto<>(true, null, schedulingServices.findAllByReceptionistId(id, pageable)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseApiDto<Void>> update(
            @PathVariable Long id,
            @RequestBody @Valid UpdateSchedulingDto dto
            ){
        schedulingServices.update(id, dto);

        return ResponseEntity.ok(new ResponseApiDto<>(true, "Agendamento atualizado com sucesso", null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApiDto<Void>> delete(
            @PathVariable Long id
    ){
        schedulingServices.delete(id);

        return ResponseEntity.ok(new ResponseApiDto<>(true, "Agendamento removido com sucesso", null));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ResponseApiDto<Void>> updateStatus(
            @PathVariable Long id,
            @RequestBody @Valid UpdateStatusSchedulingDto dto
            ){
        schedulingServices.updateStatus(id, dto);

        return ResponseEntity.ok(new ResponseApiDto<>(true, "Status do agendamento atualizado com sucesso", null));
    }
}
