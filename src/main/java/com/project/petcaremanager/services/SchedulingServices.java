package com.project.petcaremanager.services;

import com.project.petcaremanager.dtos.schedulingDtos.models.ModelSchedulingEntitiesDto;
import com.project.petcaremanager.dtos.schedulingDtos.request.CreateSchedulingDto;
import com.project.petcaremanager.dtos.schedulingDtos.request.UpdateSchedulingDto;
import com.project.petcaremanager.dtos.schedulingDtos.request.UpdateStatusSchedulingDto;
import com.project.petcaremanager.dtos.schedulingDtos.response.ResponseSchedulingDto;
import com.project.petcaremanager.exceptions.ResourceNotFoundException;
import com.project.petcaremanager.models.Pet;
import com.project.petcaremanager.models.Professional;
import com.project.petcaremanager.models.Scheduling;
import com.project.petcaremanager.models.Unit;
import com.project.petcaremanager.models.enums.PetSize;
import com.project.petcaremanager.models.enums.SchedulingStatus;
import com.project.petcaremanager.repositories.PetRepository;
import com.project.petcaremanager.repositories.ProfessionalRepository;
import com.project.petcaremanager.repositories.SchedulingRepository;
import com.project.petcaremanager.repositories.UnitRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SchedulingServices {

    private final SchedulingRepository schedulingRepository;
    private final PetRepository petRepository;
    private final ProfessionalRepository professionalRepository;
    private final UnitRepository unitRepository;

    public SchedulingServices(
            SchedulingRepository schedulingRepository,
            PetRepository petRepository,
            ProfessionalRepository professionalRepository,
            UnitRepository unitRepository) {
        this.schedulingRepository = schedulingRepository;
        this.petRepository = petRepository;
        this.professionalRepository = professionalRepository;
        this.unitRepository = unitRepository;
    }

    public void create(CreateSchedulingDto dto){
        ModelSchedulingEntitiesDto model = findAllEntitiesScheduling(
                dto.petId(),
                dto.professionalId(),
                dto.receptionistId(),
                dto.unitId());

        Scheduling scheduling = new Scheduling(
                null,
                model.pet(),
                model.professional(),
                model.receptionist(),
                model.unit(),
                dto.startDateTime(),
                dto.endDateTime(),
                dto.finalPrice(),
                dto.observation()
        );

        schedulingRepository.save(scheduling);
    }

    public Page<ResponseSchedulingDto> findAllByClientId(Long id, Pageable pageable){
        return schedulingRepository.findAllByPet_Client_Id(id, pageable)
                .map(scheduling -> new ResponseSchedulingDto(
                        scheduling.getId(),
                        scheduling.getPet().getName(),
                        scheduling.getProfessional().getUser().getName(),
                        scheduling.getReceptionist().getUser().getName(),
                        scheduling.getUnit().getName(),
                        scheduling.getStartDateTime(),
                        scheduling.getStatus()
                ));
    }

    public Page<ResponseSchedulingDto> findAllByPetId(Long id, Pageable pageable){
        return schedulingRepository.findAllByPetId(id, pageable)
                .map(scheduling -> new ResponseSchedulingDto(
                        scheduling.getId(),
                        scheduling.getPet().getName(),
                        scheduling.getProfessional().getUser().getName(),
                        scheduling.getReceptionist().getUser().getName(),
                        scheduling.getUnit().getName(),
                        scheduling.getStartDateTime(),
                        scheduling.getStatus()
                ));
    }

    public Page<ResponseSchedulingDto> findAllByUnitId(Long id, Pageable pageable){
        return schedulingRepository.findAllByUnitId(id, pageable)
                .map(scheduling -> new ResponseSchedulingDto(
                        scheduling.getId(),
                        scheduling.getPet().getName(),
                        scheduling.getProfessional().getUser().getName(),
                        scheduling.getReceptionist().getUser().getName(),
                        scheduling.getUnit().getName(),
                        scheduling.getStartDateTime(),
                        scheduling.getStatus()
                ));
    }

    public Page<ResponseSchedulingDto> findAllByProfessionalId(Long id, Pageable pageable){
        return schedulingRepository.findAllByProfessionalId(id, pageable)
                .map(scheduling -> new ResponseSchedulingDto(
                        scheduling.getId(),
                        scheduling.getPet().getName(),
                        scheduling.getProfessional().getUser().getName(),
                        scheduling.getReceptionist().getUser().getName(),
                        scheduling.getUnit().getName(),
                        scheduling.getStartDateTime(),
                        scheduling.getStatus()
                ));
    }

    public Page<ResponseSchedulingDto> findAllByReceptionistId(Long id, Pageable pageable){
        return schedulingRepository.findAllByReceptionistId(id, pageable)
                .map(scheduling -> new ResponseSchedulingDto(
                        scheduling.getId(),
                        scheduling.getPet().getName(),
                        scheduling.getProfessional().getUser().getName(),
                        scheduling.getReceptionist().getUser().getName(),
                        scheduling.getUnit().getName(),
                        scheduling.getStartDateTime(),
                        scheduling.getStatus()
                ));
    }

    public void update(Long id, UpdateSchedulingDto dto){
        ModelSchedulingEntitiesDto model = findAllEntitiesScheduling(
                dto.petId(),
                dto.professionalId(),
                dto.receptionistId(),
                dto.unitId());

        Scheduling scheduling = schedulingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhum agendamento encontrado com esse identificador " + id
                ));
        scheduling.update(dto, model);

        schedulingRepository.save(scheduling);
    }

    public void delete(Long id){
        Scheduling scheduling = schedulingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhum agendamento encontrado com esse identificador " + id
                ));

        schedulingRepository.delete(scheduling);
    }

    public void updateStatus(Long id, UpdateStatusSchedulingDto dto){
        Scheduling scheduling = schedulingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhum agendamento encontrado com esse identificador " + id
                ));

        SchedulingStatus schedulingStatus;

        try {
            schedulingStatus = SchedulingStatus.valueOf(dto.status().toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new IllegalArgumentException("Status do agendamento inválido: " + dto.status());
        }

        scheduling.setStatus(schedulingStatus);
        schedulingRepository.save(scheduling);
    }

    private ModelSchedulingEntitiesDto findAllEntitiesScheduling(
            Long petId,
            Long professionalId,
            Long receptionistId,
            Long unitId){
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhum pet foi encontrado com esse identificador " + petId
                ));
        Professional professional = professionalRepository.findById(professionalId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhum profissional foi encontrado com esse identificador " + professionalId
                ));
        Professional receptionist = professionalRepository.findById(professionalId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhum recepcionista foi encontrado com esse identificador " + receptionistId
                ));
        Unit unit = unitRepository.findById(unitId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhum unidade encontrada com esse identificador " + unitId
                ));

        return new ModelSchedulingEntitiesDto(
                pet,
                professional,
                receptionist,
                unit
        );
    }
}
