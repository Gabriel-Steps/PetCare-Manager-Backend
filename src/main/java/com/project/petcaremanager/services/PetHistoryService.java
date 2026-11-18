package com.project.petcaremanager.services;

import com.project.petcaremanager.dtos.petHistory.request.CreatePetHistoryDto;
import com.project.petcaremanager.dtos.petHistory.response.ResponsePetHistoryDto;
import com.project.petcaremanager.exceptions.ResourceNotFoundException;
import com.project.petcaremanager.models.Pet;
import com.project.petcaremanager.models.PetHistory;
import com.project.petcaremanager.models.enums.ServiceCategory;
import com.project.petcaremanager.repositories.PetHistoryRepository;
import com.project.petcaremanager.repositories.PetRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class PetHistoryService {

    private final PetHistoryRepository petHistoryRepository;
    private final PetRepository petRepository;

    public PetHistoryService(PetHistoryRepository petHistoryRepository, PetRepository petRepository){
        this.petHistoryRepository = petHistoryRepository;
        this.petRepository = petRepository;
    }

    public void create(CreatePetHistoryDto dto){
        Pet pet = petRepository.findById(dto.petId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhum pet encotrado com esse identificador " + dto.petId()
                ));

        ServiceCategory serviceCategory;

        try {
            serviceCategory = ServiceCategory.valueOf(dto.category().toUpperCase());
        }catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Categoria do serviço inválido: " + dto.category());
        }

        PetHistory petHistory = new PetHistory(
                null,
                pet,
                serviceCategory,
                dto.nameService()
        );

        petHistoryRepository.save(petHistory);
    }

    public Page<ResponsePetHistoryDto> findAllByPetId(Long id){
        return petHistoryRepository.findAllByPetId(id)
                .map(petHistory -> new ResponsePetHistoryDto(
                        petHistory.getId(),
                        petHistory.getPet().getName(),
                        petHistory.getCategory(),
                        petHistory.getNameService(),
                        petHistory.getServiceDay()
                ));
    }
}
