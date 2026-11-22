package com.project.petcaremanager.services;

import com.project.petcaremanager.dtos.unitDtos.request.CreateUnitDto;
import com.project.petcaremanager.dtos.unitDtos.request.UpdateUnitDto;
import com.project.petcaremanager.dtos.unitDtos.response.ResponseUnitDto;
import com.project.petcaremanager.exceptions.ResourceNotFoundException;
import com.project.petcaremanager.models.Address;
import com.project.petcaremanager.models.Unit;
import com.project.petcaremanager.models.User;
import com.project.petcaremanager.repositories.AddressRepository;
import com.project.petcaremanager.repositories.UnitRepository;
import com.project.petcaremanager.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UnitService {

    private final UnitRepository unitRepository;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public UnitService(
            UnitRepository unitRepository,
            AddressRepository addressRepository,
            UserRepository userRepository) {
        this.unitRepository = unitRepository;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    public void create(CreateUnitDto dto){
        Address address = addressRepository.findById(dto.addressId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhum endereço encontrado com o identificador " + dto.addressId()
                ));

        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhum usuário encontrado com o identificador " + dto.userId()
                ));

        Unit unit = new Unit(
                null,
                dto.name(),
                dto.cnpj(),
                dto.companyName(),
                dto.phone(),
                dto.email(),
                address,
                user
        );

        unitRepository.save(unit);
    }

    public Page<ResponseUnitDto> findAllByUserId(Long id, Pageable pageable){
        return unitRepository.findAllByUserId(id, pageable)
                .map(unit -> new ResponseUnitDto(
                        unit.getId(),
                        unit.getName(),
                        unit.getCnpj(),
                        unit.getCompanyName(),
                        unit.getPhone(),
                        unit.getEmail(),
                        unit.getAddress()
                ));
    }

    public void updateActiveStatus(Long id){
        Unit unit = unitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhuma unidade encontrada com esse identificador " + id
                ));

        unit.changeStatus();
        unitRepository.save(unit);
    }

    public void update(Long id, UpdateUnitDto dto){
        Unit unit = unitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhuma unidade encontrada com esse identificador " + id
                ));

        unit.update(dto);
        unitRepository.save(unit);
    }

    public ResponseUnitDto findById(Long id){
        Unit unit = unitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhuma unidade encontrada com esse identificador " + id
                ));

        return new ResponseUnitDto(
                unit.getId(),
                unit.getName(),
                unit.getCnpj(),
                unit.getCompanyName(),
                unit.getPhone(),
                unit.getEmail(),
                unit.getAddress()
        );
    }
}