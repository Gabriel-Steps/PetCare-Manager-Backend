package com.project.petcaremanager.controllers;

import com.project.petcaremanager.dtos.ResponseApiDto;
import com.project.petcaremanager.dtos.addressDtos.request.CreateAddressDto;
import com.project.petcaremanager.dtos.addressDtos.request.UpdateAddressDto;
import com.project.petcaremanager.dtos.addressDtos.response.ResponseAddressDto;
import com.project.petcaremanager.services.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService){
        this.addressService = addressService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseApiDto<Void> create(@RequestBody @Valid CreateAddressDto dto){
        addressService.create(dto);

        return new ResponseApiDto<>(true, "Endereço cadastrado com sucesso", null);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseApiDto<ResponseAddressDto>> findById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(new ResponseApiDto<>(true, null, addressService.findById(id)));
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseApiDto<Void>> update(
            @PathVariable Long id,
            @RequestBody @Valid UpdateAddressDto dto
    ){
        addressService.update(id, dto);

        return ResponseEntity.ok(new ResponseApiDto<>(true, "Endereço atualizado com sucesso", null));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseApiDto<Void>> delete(
            @PathVariable Long id
    ){
        addressService.delete(id);

        return ResponseEntity.ok(new ResponseApiDto<>(true, "Endereço removido com sucesso", null));
    }
}
