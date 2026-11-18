package com.project.petcaremanager.services;

import com.project.petcaremanager.dtos.petDtos.request.CreatePetDto;
import com.project.petcaremanager.dtos.petDtos.request.UpdatePetDto;
import com.project.petcaremanager.dtos.petDtos.response.ResponsePetDto;
import com.project.petcaremanager.exceptions.ResourceNotFoundException;
import com.project.petcaremanager.models.Client;
import com.project.petcaremanager.models.Pet;
import com.project.petcaremanager.models.enums.PetSize;
import com.project.petcaremanager.repositories.ClientRepository;
import com.project.petcaremanager.repositories.PetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final ClientRepository clientRepository;

    public PetService(PetRepository petRepository, ClientRepository clientRepository){
        this.petRepository = petRepository;
        this.clientRepository = clientRepository;
    }

    public void create(CreatePetDto dto){
        Client client = clientRepository.findById(dto.clientId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhum cliente encontrado com esse identificador " + dto.clientId()
                ));

        PetSize petSize;

        try {
            petSize = PetSize.valueOf(dto.size().toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Tamanho de pet inválido: " + dto.size());
        }

        Pet pet = new Pet(
                null,
                client,
                dto.name(),
                dto.species(),
                dto.race(),
                dto.sex(),
                dto.neutered(),
                dto.dateOfBirth(),
                dto.weight(),
                petSize,
                dto.color(),
                dto.allergy(),
                dto.temperament(),
                dto.numberMicroship()
        );

        petRepository.save(pet);
    }

    public Page<ResponsePetDto> findAllByClientId(Long id, Pageable pageable){
        return petRepository.findAllByClientId(id, pageable)
                .map(pet -> new ResponsePetDto(
                        pet.getId(),
                        pet.getName(),
                        pet.getSpecies(),
                        pet.getRace(),
                        pet.getSex(),
                        pet.isNeutered(),
                        pet.getDateOfBirth(),
                        pet.getWeight(),
                        pet.getSize(),
                        pet.getColor(),
                        pet.getAllergy(),
                        pet.getTemperament(),
                        pet.getNumberMicroship()
                ));
    }

    public void update(UpdatePetDto dto, Long id){
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhum pet encontrado com o identificador " + id
                ));

        PetSize petSize;

        try {
            petSize = PetSize.valueOf(dto.size().toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Tamanho de pet inválido: " + dto.size());
        }

        pet.update(dto, petSize);

        petRepository.save(pet);
    }

    public void delete(Long id){
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhum pet encontrado com o identificador " + id
                ));

        petRepository.delete(pet);
    }
}
