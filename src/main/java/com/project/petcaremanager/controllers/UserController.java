package com.project.petcaremanager.controllers;

import com.project.petcaremanager.dtos.userDtos.GetUsersDto;
import com.project.petcaremanager.dtos.userDtos.UpdateUserDto;
import com.project.petcaremanager.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @GetMapping
    public List<GetUsersDto> findAll(){
        return service.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<GetUsersDto> update(
            @PathVariable Long id,
            @RequestBody @Valid UpdateUserDto dto
    ){
        if (id <= 0){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if (id <= 0){
            return ResponseEntity.badRequest().build();
        }

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
