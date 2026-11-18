package com.project.petcaremanager.services;

import com.project.petcaremanager.dtos.clientDtos.request.CreateClientDto;
import com.project.petcaremanager.dtos.clientDtos.request.UpdateClientDto;
import com.project.petcaremanager.dtos.clientDtos.response.ResponseClientDto;
import com.project.petcaremanager.exceptions.ResourceNotFoundException;
import com.project.petcaremanager.models.Address;
import com.project.petcaremanager.models.Client;
import com.project.petcaremanager.repositories.AddressRepository;
import com.project.petcaremanager.repositories.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;

    public ClientService(ClientRepository clientRepository, AddressRepository addressRepository){
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
    }

    public void create(CreateClientDto dto){
        Address address = addressRepository.findById(dto.addressId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhum endereço encontrado com esse identicador " + dto.addressId()
                ));
        Client client = new Client(
                null,
                dto.name(),
                dto.cpf(),
                dto.phone(),
                dto.secondaryPhone(),
                dto.email(),
                address,
                dto.contactViaWhatsApp(),
                dto.observation()
        );

        clientRepository.save(client);
    }

    public ResponseClientDto findById(Long id){
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhum usuário econtrado com esse identificador " + id
                ));

        return new ResponseClientDto(
                id,
                client.getName(),
                client.getCpf(),
                client.getEmail(),
                client.getPhone(),
                client.getSecondaryPhone(),
                client.getAddress(),
                client.isContactViaWhatsApp(),
                client.getObservation(),
                client.isActive(),
                client.getCreatedAt()
        );
    }

    public void update(Long id, UpdateClientDto dto){
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhum usuário econtrado com esse identificador " + id
                ));

        client.updateClient(dto);
        clientRepository.save(client);
    }

    public void delete(Long id){
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhum usuário econtrado com esse identificador " + id
                ));

        clientRepository.delete(client);
    }
}

