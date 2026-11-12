package com.project.petcaremanager.services;

import com.project.petcaremanager.dtos.addressDtos.request.CreateAddressDto;
import com.project.petcaremanager.dtos.addressDtos.request.UpdateAddressDto;
import com.project.petcaremanager.dtos.addressDtos.response.ResponseAddressDto;
import com.project.petcaremanager.exceptions.ResourceNotFoundException;
import com.project.petcaremanager.models.Address;
import com.project.petcaremanager.models.User;
import com.project.petcaremanager.repositories.AddressRepository;
import com.project.petcaremanager.repositories.ClientRepository;
import com.project.petcaremanager.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final ClientRepository clientRepository;

    public AddressService(AddressRepository addressRepository, ClientRepository clientRepository){
        this.addressRepository = addressRepository;
        this.clientRepository = clientRepository;
    }

    public void create(CreateAddressDto dto){
        Address address = new Address(
                null,
                dto.street(),
                dto.number(),
                dto.supplement(),
                dto.neighborhood(),
                dto.city(),
                dto.uf()
        );

        addressRepository.save(address);
    }

    public ResponseAddressDto findById(Long id){
        Address address =  addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhum endereço encontrado com o identificador " + id
                ));

        return new ResponseAddressDto(
                address.getId(),
                address.getStreet(),
                address.getNumber(),
                address.getSupplement(),
                address.getNeighborhood(),
                address.getCity(),
                address.getUf()
        );
    }

    public void update(Long id, UpdateAddressDto dto){

        Address address =  addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhum endereço encontrado com o identificador " + id
                ));


        address.update(dto);
        addressRepository.save(address);
    }

    public void delete(Long id){
        Address address =  addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhum endereço encontrado com o identificador " + id
                ));

        addressRepository.delete(address);
    }
}
