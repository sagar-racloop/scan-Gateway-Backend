package com.abdm.sharegateway.Controller;

import com.abdm.sharegateway.DTO.PatientDTO;
import com.abdm.sharegateway.Service.Serviceclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class Controller {

    @Autowired
    private Serviceclass service;

    @PostMapping("/add/patient")
    public String addPatient(@RequestBody PatientDTO patientDTO)
    {
        service.add(patientDTO);
        return "Patient Added ";
    }



}
