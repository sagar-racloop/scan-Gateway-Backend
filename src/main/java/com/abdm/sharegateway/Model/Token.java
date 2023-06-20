package com.abdm.sharegateway.Model;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "token_number")
    private String tokenNumber = UUID.randomUUID().toString();

    @CreationTimestamp
    @Column(name = "generation_time")
    private LocalDate generationTime;

    private boolean status;

    @OneToOne
    @JoinColumn
    private Patient patient;

    public Token() {
    }

    public String getTokenNumber() {
        return tokenNumber;
    }

    public void setTokenNumber(String tokenNumber) {
        this.tokenNumber = tokenNumber;
    }

    public LocalDate getGenerationTime() {
        return generationTime;
    }

    public void setGenerationTime(LocalDate generationTime) {
        this.generationTime = generationTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
