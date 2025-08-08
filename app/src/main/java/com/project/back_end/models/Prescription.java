package com.project.back_end.models;

import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "prescriptions")
public class Prescription {
    @JsonProperty("id")
    private String id;

    @NotNull
    @Size(min = 3, max = 100)
    @JsonProperty("patientName")
    private String patientName;

    @NotNull
    @Size(min = 3, max = 100)
    @JsonProperty("medication")
    private String medication;

    @NotNull
    @JsonProperty("dosage")
    private String dosage;

    @Size(max = 200)
    @JsonProperty("doctorNotes")
    private String doctorNotes;

    @NotNull
    @JsonProperty("appointmentId")
    private Long appointmentId;

    // Constructors
    public Prescription() {}
    public Prescription(String patientName, String medication, String dosage, String doctorNotes, Long appointmentId) {
        this.patientName = patientName;
        this.medication = medication;
        this.dosage = dosage;
        this.doctorNotes = doctorNotes;
        this.appointmentId = appointmentId;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    public String getMedication() { return medication; }
    public void setMedication(String medication) { this.medication = medication; }
    public String getDosage() { return dosage; }
    public void setDosage(String dosage) { this.dosage = dosage; }
    public String getDoctorNotes() { return doctorNotes; }
    public void setDoctorNotes(String doctorNotes) { this.doctorNotes = doctorNotes; }
    public Long getAppointmentId() { return appointmentId; }
    public void setAppointmentId(Long appointmentId) { this.appointmentId = appointmentId; }
}