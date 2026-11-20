package com.project.petcaremanager.services;

import com.project.petcaremanager.dtos.serviceDtos.request.CreateServiceDto;
import com.project.petcaremanager.dtos.serviceDtos.request.UpdateServiceDto;
import com.project.petcaremanager.dtos.serviceDtos.response.ResponseServiceDto;
import com.project.petcaremanager.exceptions.ResourceNotFoundException;
import com.project.petcaremanager.models.Services;
import com.project.petcaremanager.models.enums.PetSize;
import com.project.petcaremanager.models.enums.ServiceCategory;
import com.project.petcaremanager.repositories.ServiceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ServiceServices {

    private final ServiceRepository serviceRepository;

    public ServiceServices(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public void create(CreateServiceDto dto){

        ServiceCategory serviceCategory;

        try {
            serviceCategory = ServiceCategory.valueOf(dto.category().toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Categoria do serviço inválido: " + dto.category());
        }

        Services service = new Services(
                null,
                dto.name(),
                serviceCategory,
                dto.description(),
                dto.duration(),
                dto.basePrice(),
                dto.requiresVeterinarian()
        );

        serviceRepository.save(service);
    }

    public Page<ResponseServiceDto> findAll(Pageable pageable){
        return serviceRepository.findAll(pageable)
                .map(services -> new ResponseServiceDto(
                        null,
                        services.getName(),
                        services.getCategory(),
                        services.getDescription(),
                        services.getDuration(),
                        services.getBasePrice(),
                        services.isRequiresVeterinarian()
                ));
    }

    public ResponseServiceDto findById(Long id){
        Services service = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhum serviço encontrado com esse identificador " + id
                ));

        return new ResponseServiceDto(
                service.getId(),
                service.getName(),
                service.getCategory(),
                service.getDescription(),
                service.getDuration(),
                service.getBasePrice(),
                service.isRequiresVeterinarian()
        );
    }

    public void update(Long id, UpdateServiceDto dto){

        Services service = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhum serviço encontrado com o identificador " + id
                ));

        ServiceCategory serviceCategory;
        try {
            serviceCategory = ServiceCategory.valueOf(dto.category().toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Categoria do serviço inválido: " + dto.category());
        }

        service.update(dto, serviceCategory);
        serviceRepository.save(service);
    }

    public void delete(Long id){
        Services service = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhum serviço encontrado com o identificador " + id
                ));

        serviceRepository.delete(service);
    }
}
