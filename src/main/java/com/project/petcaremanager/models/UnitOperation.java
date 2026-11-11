package com.project.petcaremanager.models;

import com.project.petcaremanager.models.enums.DayOfWeek;
import jakarta.persistence.*;

import java.util.TimeZone;

@Entity
@Table(name = "unit_operations")
public class UnitOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DayOfWeek dayOfWeek;

    @Column(nullable = false)
    private TimeZone startTime;

    @Column(nullable = false)
    private TimeZone endTime;

    public UnitOperation(
            Long id,
            Unit unit,
            DayOfWeek dayOfWeek,
            TimeZone startTime,
            TimeZone endTime) {
        this.id = id;
        this.unit = unit;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public TimeZone getStartTime() {
        return startTime;
    }

    public void setStartTime(TimeZone startTime) {
        this.startTime = startTime;
    }

    public TimeZone getEndTime() {
        return endTime;
    }

    public void setEndTime(TimeZone endTime) {
        this.endTime = endTime;
    }
}
