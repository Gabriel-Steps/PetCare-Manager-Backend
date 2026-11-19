package com.project.petcaremanager.services;

import com.project.petcaremanager.dtos.professionalDtos.request.CreateProfessionalDto;
import com.project.petcaremanager.dtos.professionalDtos.request.UpdateProfessionalDto;
import com.project.petcaremanager.dtos.professionalDtos.response.ResponseProfessionalDto;
import com.project.petcaremanager.exceptions.ResourceNotFoundException;
import com.project.petcaremanager.models.Professional;
import com.project.petcaremanager.models.User;
import com.project.petcaremanager.repositories.ProfessionalRepository;
import com.project.petcaremanager.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProfessionalService {

    private final ProfessionalRepository professionalRepository;
    private final UserRepository userRepository;

    public ProfessionalService(ProfessionalRepository professionalRepository, UserRepository userRepository){
        this.professionalRepository = professionalRepository;
        this.userRepository = userRepository;
    }

    public void create(CreateProfessionalDto dto){
        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhum usuário encontrado com esse identificador " + dto.userId()
                ));

        Professional professional = new Professional(
                null,
                user,
                dto.specialty(),
                dto.crmv(),
                dto.phone()
        );

        professionalRepository.save(professional);
    }

    public Page<ResponseProfessionalDto> findAllByUnitId(Long id, Pageable pageable){
        return professionalRepository.findAllByUser_Unit_Id(id, pageable)
                .map(professional -> new ResponseProfessionalDto(
                        professional.getId(),
                        professional.getUser().getId(),
                        professional.getUser().getName(),
                        professional.getUser().getEmail(),
                        professional.getUser().getUnit().getName()
                ));
    }

    public void update(Long id, UpdateProfessionalDto dto){
        Professional professional = professionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhum profissional encontrado com o identificador " + id
                ));

        professional.update(dto);
        professionalRepository.save(professional);
    }

    public void delete(Long id){
        Professional professional = professionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhum profissional encontrado com o identificador " + id
                ));

        professionalRepository.delete(professional);
    }
}
