package com.project.petcaremanager.controllers;

import com.project.petcaremanager.dtos.ResponseApiDto;
import com.project.petcaremanager.dtos.userDtos.CreateUserDto;
import com.project.petcaremanager.dtos.userDtos.AccessResponseDto;
import com.project.petcaremanager.dtos.userDtos.LoginUserDto;
import com.project.petcaremanager.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service){
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseApiDto<AccessResponseDto>> login(@RequestBody @Valid LoginUserDto dto){
        return ResponseEntity.ok(
                new ResponseApiDto<>(true, "Usuário logado com sucesso", service.login(dto))
        );
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseApiDto<AccessResponseDto> create(@RequestBody  @Valid CreateUserDto dto){
        return new ResponseApiDto<>(true, "Usuário cadastrado com sucesso", service.create(dto));
    }
}
