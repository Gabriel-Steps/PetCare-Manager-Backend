package com.project.petcaremanager.services;

import com.project.petcaremanager.dtos.unitOperationDtos.request.CreateUnitOperationsDto;
import com.project.petcaremanager.dtos.unitOperationDtos.request.UpdateUnitOperationDto;
import com.project.petcaremanager.dtos.unitOperationDtos.response.ResponseUnitOperationDto;
import com.project.petcaremanager.exceptions.ResourceNotFoundException;
import com.project.petcaremanager.models.Unit;
import com.project.petcaremanager.models.UnitOperation;
import com.project.petcaremanager.models.enums.DayOfWeek;
import com.project.petcaremanager.repositories.UnitOperationRepository;
import com.project.petcaremanager.repositories.UnitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Service
public class UnitOperationService {

    private final UnitOperationRepository unitOperationRepository;
    private final UnitRepository unitRepository;

    public UnitOperationService(
            UnitOperationRepository unitOperationRepository,
            UnitRepository unitRepository) {
        this.unitOperationRepository = unitOperationRepository;
        this.unitRepository = unitRepository;
    }

    public void create(List<CreateUnitOperationsDto> dto){
        Unit unit = unitRepository.findById(dto.getFirst().unitId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhuma unidade encontrada com esse identificador " + dto.getFirst().unitId()
                ));

        List<UnitOperation> unitOperationList = dto
                .stream().map(unitOperation -> {

                    DayOfWeek dayOfWeek;
                    try {
                        dayOfWeek = DayOfWeek.valueOf(unitOperation.dayOfWeek().toUpperCase());
                    } catch (IllegalArgumentException | NullPointerException e) {
                        throw new IllegalArgumentException("Dia da semana inválido: " + unitOperation.dayOfWeek());
                    }

                    return new UnitOperation(
                            null,
                            unit,
                            dayOfWeek,
                            unitOperation.startTime(),
                            unitOperation.endTime()
                    );
                }).toList();

        unitOperationRepository.saveAll(unitOperationList);
    }

    public List<ResponseUnitOperationDto> findAllByUnitId(Long id){
        return unitOperationRepository.findAllByUnitId(id)
                .stream().map(unitOperation -> new ResponseUnitOperationDto(
                        unitOperation.getId(),
                        unitOperation.getDayOfWeek(),
                        unitOperation.getStartTime(),
                        unitOperation.getEndTime()
                )).toList();
    }

    public void updateAllByUnitId(
            Long id,
            List<UpdateUnitOperationDto> dtoList
    ) {
        Unit unit = unitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Unidade não encontrada: " + id
                ));

        List<UnitOperation> operations = dtoList.stream()
                .map(dto -> {

                    if (!Objects.equals(dto.unitId(), id)) {
                        throw new IllegalArgumentException(
                                "A operação não pertence à unidade informada"
                        );
                    }

                    UnitOperation operation = unitOperationRepository
                            .findByIdAndUnitId(dto.unitOperation(), id)
                            .orElseThrow(() -> new ResourceNotFoundException(
                                    "Operação não encontrada: " + dto.unitOperation()
                            ));

                    DayOfWeek dayOfWeek = DayOfWeek.valueOf(
                            dto.dayOfWeek().toUpperCase(Locale.ROOT)
                    );

                    operation.setDayOfWeek(dayOfWeek);
                    operation.setStartTime(dto.startTime());
                    operation.setEndTime(dto.endTime());

                    return operation;
                })
                .toList();

        unitOperationRepository.saveAll(operations);
    }
}
