package com.project.petcaremanager.models;

import jakarta.persistence.*;

@Entity
@Table(name = "scheduling_service")
public class SchedulingService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "scheduling_id")
    private Scheduling scheduling;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    public SchedulingService() { }

    public SchedulingService(
            Long id,
            Scheduling scheduling,
            Service service) {
        this.id = id;
        this.scheduling = scheduling;
        this.service = service;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public Scheduling getScheduling() {
        return scheduling;
    }

    public void setScheduling(Scheduling scheduling) {
        this.scheduling = scheduling;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
