package com.project.petcaremanager.services;

import com.project.petcaremanager.dtos.vaccinationRecordDtos.request.CreateVaccinationRecordDto;
import com.project.petcaremanager.dtos.vaccinationRecordDtos.response.ResponseVaccinationRecordDto;
import com.project.petcaremanager.exceptions.ResourceNotFoundException;
import com.project.petcaremanager.models.Pet;
import com.project.petcaremanager.models.VaccinationRecord;
import com.project.petcaremanager.repositories.PetRepository;
import com.project.petcaremanager.repositories.VaccinationRecordRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VaccinationRecordService {

    private final VaccinationRecordRepository vaccinationRecordRepository;
    private final PetRepository petRepository;

    public VaccinationRecordService(
            VaccinationRecordRepository vaccinationRecordRepository,
            PetRepository petRepository) {
        this.vaccinationRecordRepository = vaccinationRecordRepository;
        this.petRepository = petRepository;
    }

    public void create(CreateVaccinationRecordDto dto){
        Pet pet = petRepository.findById(dto.petId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhum pet encontrado com esse identificador " + dto.petId()
                ));

        VaccinationRecord vaccinationRecord = new VaccinationRecord(
                null,
                dto.name(),
                pet,
                dto.coveredConditions(),
                dto.vaccinationDay(),
                dto.lotNumber(),
                dto.manufacturer(),
                dto.boosterVaccine()
        );

        vaccinationRecordRepository.save(vaccinationRecord);
    }

    public Page<ResponseVaccinationRecordDto> findAllByPetId(Long id, Pageable pageable){
        return vaccinationRecordRepository.findAllByPetId(id, pageable)
                .map(vaccinationRecord -> new ResponseVaccinationRecordDto(
                        vaccinationRecord.getId(),
                        vaccinationRecord.getPet().getName(),
                        vaccinationRecord.getName(),
                        vaccinationRecord.getCoveredConditions(),
                        vaccinationRecord.getVaccinationDay(),
                        vaccinationRecord.getLotNumber(),
                        vaccinationRecord.getManufacturer(),
                        vaccinationRecord.getBoosterVaccine()
                ));
    }
}
