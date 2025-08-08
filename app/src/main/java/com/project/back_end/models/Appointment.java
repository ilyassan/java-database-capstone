package com.project.back_end.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Future;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @NotNull
    @JsonProperty("doctor")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @NotNull
    @JsonProperty("patient")
    private Patient patient;

    @NotNull
    @Future
    @JsonProperty("appointmentTime")
    private LocalDateTime appointmentTime;

    @NotNull
    @JsonProperty("status")
    private int status = 0; // 0 = Scheduled, 1 = Completed, 2 = Cancelled

    // Helper methods
    public LocalDateTime getEndTime() {
        return appointmentTime.plusHours(1); // Assumes 1-hour appointments
    }

    public LocalDate getAppointmentDate() {
        return appointmentTime.toLocalDate();
    }

    public LocalTime getAppointmentTimeOnly() {
        return appointmentTime.toLocalTime();
    }

    // Constructors
    public Appointment() {}
    public Appointment(Doctor doctor, Patient patient, LocalDateTime appointmentTime, int status) {
        this.doctor = doctor;
        this.patient = patient;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
    public LocalDateTime getAppointmentTime() { return appointmentTime; }
    public void setAppointmentTime(LocalDateTime appointmentTime) { this.appointmentTime = appointmentTime; }
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
}