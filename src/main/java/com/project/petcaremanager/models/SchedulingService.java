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
    private Services services;

    public SchedulingService() { }

    public SchedulingService(
            Long id,
            Scheduling scheduling,
            Services services) {
        this.id = id;
        this.scheduling = scheduling;
        this.services = services;
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

    public Services getService() {
        return services;
    }

    public void setService(Services services) {
        this.services = services;
    }
}
