package com.project.petcaremanager.controllers;

import com.project.petcaremanager.dtos.ResponseApiDto;
import com.project.petcaremanager.dtos.clientDtos.request.CreateClientDto;
import com.project.petcaremanager.dtos.clientDtos.request.UpdateClientDto;
import com.project.petcaremanager.dtos.clientDtos.response.ResponseClientDto;
import com.project.petcaremanager.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseApiDto<Void> create(
            @RequestBody @Valid CreateClientDto dto
    ){
        clientService.create(dto);

        return new ResponseApiDto<>(true, "Cliente cadastrado com sucesso", null);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseApiDto<ResponseClientDto>> findById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(new ResponseApiDto<>(
                true, null, clientService.findById(id)
        ));
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseApiDto<Void>> update(
            @RequestBody @Valid UpdateClientDto dto,
            @PathVariable Long id
    ){
        clientService.update(id, dto);

        return ResponseEntity.ok(new ResponseApiDto<>(
                true, "Cliente atualizado com sucesso", null
        ));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseApiDto<Void>> delete(
            @PathVariable Long id
    ){
        clientService.delete(id);

        return ResponseEntity.ok(new ResponseApiDto<>(
                true, "Cliente deletado com sucesso", null
        ));
    }
}
