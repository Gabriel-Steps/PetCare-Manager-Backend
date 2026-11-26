package com.project.petcaremanager.controllers;

import com.project.petcaremanager.dtos.ResponseApiDto;
import com.project.petcaremanager.dtos.serviceDtos.request.CreateServiceDto;
import com.project.petcaremanager.dtos.serviceDtos.request.UpdateServiceDto;
import com.project.petcaremanager.dtos.serviceDtos.response.ResponseServiceDto;
import com.project.petcaremanager.services.ServiceServices;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/service")
public class ServiceController {

    private final ServiceServices serviceServices;

    public ServiceController(ServiceServices serviceServices) {
        this.serviceServices = serviceServices;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseApiDto<Void> create(
            @RequestBody @Valid CreateServiceDto dto
            ){

        serviceServices.create(dto);

        return new ResponseApiDto<>(true, "Serviço criado com sucesso", null);
    }

    @GetMapping
    public ResponseEntity<ResponseApiDto<Page<ResponseServiceDto>>> findAll(
            Pageable pageable
    ){
        return ResponseEntity.ok(new ResponseApiDto<>(true, null, serviceServices.findAll(pageable)));
    }

    @GetMapping("/unit/{id}")
    public ResponseEntity<ResponseApiDto<Page<ResponseServiceDto>>> findAll(
            @PathVariable Long id,
            Pageable pageable
    ){
        return ResponseEntity.ok(new ResponseApiDto<>(true, null, serviceServices.findAllByUnitId(id, pageable)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseApiDto<Void>> update(
            @PathVariable Long id,
            @RequestBody @Valid UpdateServiceDto dto
            ){
        serviceServices.update(id, dto);
        return ResponseEntity.ok(new ResponseApiDto<>(true, "Serviço atualizado com sucesso", null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApiDto<Void>> delete(
            @PathVariable Long id
    ){
        serviceServices.delete(id);
        return ResponseEntity.ok(new ResponseApiDto<>(true, "Serviço removido com sucesso!", null));
    }
}
