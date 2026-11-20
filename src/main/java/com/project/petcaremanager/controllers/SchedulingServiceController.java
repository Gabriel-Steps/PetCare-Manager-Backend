package com.project.petcaremanager.controllers;

import com.project.petcaremanager.dtos.ResponseApiDto;
import com.project.petcaremanager.dtos.schedulingServiceDtos.request.CreateSchedulingServiceDto;
import com.project.petcaremanager.dtos.schedulingServiceDtos.response.ResponseSchedulingServiceDto;
import com.project.petcaremanager.services.SchedulingServiceServices;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/scheduling-service")
public class SchedulingServiceController {

    private final SchedulingServiceServices schedulingServiceServices;

    public SchedulingServiceController(SchedulingServiceServices schedulingServiceServices) {
        this.schedulingServiceServices = schedulingServiceServices;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseApiDto<Void> create(
            @RequestBody @Valid CreateSchedulingServiceDto dto
            ){

        schedulingServiceServices.create(dto);

        return new ResponseApiDto<>(true, null, null);
    }

    @GetMapping("/scheduling/{id}")
    public ResponseEntity<ResponseApiDto<Page<ResponseSchedulingServiceDto>>> findAllBySchedulingId(
            @PathVariable Long id,
            Pageable pageable
    ){

        return ResponseEntity.ok(new ResponseApiDto<>(true, null, schedulingServiceServices.findAllBySchedulingId(id, pageable)));
    }

    @DeleteMapping("/scheduling/{id}")
    public ResponseEntity<ResponseApiDto<Void>> deleteAllBySchedulingId(
            @PathVariable Long id
    ){
        schedulingServiceServices.deleteAllBySchedulingId(id);

        return ResponseEntity.ok(new ResponseApiDto<>(true, null, null));
    }

}
