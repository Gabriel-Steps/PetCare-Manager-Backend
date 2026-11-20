package com.project.petcaremanager.services;

import com.project.petcaremanager.dtos.schedulingServiceDtos.request.CreateSchedulingServiceDto;
import com.project.petcaremanager.dtos.schedulingServiceDtos.response.ResponseSchedulingServiceDto;
import com.project.petcaremanager.exceptions.ResourceNotFoundException;
import com.project.petcaremanager.models.Scheduling;
import com.project.petcaremanager.models.SchedulingService;
import com.project.petcaremanager.models.Services;
import com.project.petcaremanager.repositories.SchedulingRepository;
import com.project.petcaremanager.repositories.SchedulingServiceRepository;
import com.project.petcaremanager.repositories.ServiceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulingServiceServices {

    private final SchedulingServiceRepository schedulingServiceRepository;
    private final SchedulingRepository schedulingRepository;
    private final ServiceRepository serviceRepository;

    public SchedulingServiceServices(
            SchedulingServiceRepository schedulingServiceRepository,
            SchedulingRepository schedulingRepository,
            ServiceRepository serviceRepository) {
        this.schedulingServiceRepository = schedulingServiceRepository;
        this.schedulingRepository = schedulingRepository;
        this.serviceRepository = serviceRepository;
    }

    public void create(CreateSchedulingServiceDto dto){
        Scheduling scheduling = schedulingRepository.findById(dto.schedulingId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Nenhum agendamento encontrado com o identificador " + dto.schedulingId()
                ));
        List<Services> services = serviceRepository.findAllById(dto.servicesId());

        if(services.size() != dto.servicesId().size()){
            throw new ResourceNotFoundException(
                    "Serviço não encontrado"
            );
        }

        List<SchedulingService> schedulingServices = services
                .stream().map(service -> {

                    return new SchedulingService(
                            null,
                            scheduling,
                            service
                    );
                }).toList();

        schedulingServiceRepository.saveAll(schedulingServices);
    }

    public Page<ResponseSchedulingServiceDto> findAllBySchedulingId(Long id, Pageable pageable){
        return schedulingServiceRepository.findAllBySchedulingId(id, pageable)
                .map(schedulingService -> new ResponseSchedulingServiceDto(
                        schedulingService.getScheduling().getId(),
                        schedulingService.getService().getId(),
                        schedulingService.getService().getName(),
                        schedulingService.getService().getCategory(),
                        schedulingService.getService().getDescription(),
                        schedulingService.getService().getDuration(),
                        schedulingService.getService().getBasePrice(),
                        schedulingService.getService().isRequiresVeterinarian()
                ));
    }

    public void deleteAllBySchedulingId(Long id){
        schedulingServiceRepository.deleteAllBySchedulingId(id);
    }
}
