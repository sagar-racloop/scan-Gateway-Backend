package com.abdm.sharegateway.Service;

import com.abdm.sharegateway.DTO.PatientDTO;
import com.abdm.sharegateway.Model.Patient;
import com.abdm.sharegateway.Model.Token;
import com.abdm.sharegateway.Repository.PatientRepository;
import com.abdm.sharegateway.Repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class Serviceclass {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private TokenRepository tokenRepository;
//    public void add(PatientDTO patientDTO) {
//
//        Patient patient = new Patient();
//        patient.setAddress(patientDTO.getAddress());
//        patient.setBirthdate(patientDTO.getBirthdate());
//        patient.setCounterNo(patientDTO.getCounterNo());
//        patient.setEmail(patientDTO.getEmail());
//        patient.setHpid(patientDTO.getHpid());
//        patient.setName(patientDTO.getName());
//        patient.setGender(patientDTO.getGender());
//        patient.setMobNo(patientDTO.getMobNo());
//        Token token = new Token();
//        token.setStatus(true);
//        token.setPatient(patient);
//        patient.setToken(token);
//        patientRepository.save(patient);
//
//    }
//
//    @Scheduled(cron = "0 0 * * *")
//    public void schedule()
//    {
//        LocalDate date = LocalDate.now().minusDays(1);
//        List<Token> list = tokenRepository.findByGenerationTime(date);
//
//        for(int i = 0 ; i < list.size(); i++)
//        {
//            Token token = list.get(i);
//            token.setStatus(false);
//            list.set(i,token);
//        }
//
//        tokenRepository.saveAll(list);
//    }

    private int nextTokenNumber = 1;
    private LocalDate previousDay;

    public void add(PatientDTO patientDTO) {
        Patient patient = new Patient();
        patient.setAddress(patientDTO.getAddress());
        patient.setBirthdate(patientDTO.getBirthdate());
        patient.setCounterNo(patientDTO.getCounterNo());
        patient.setEmail(patientDTO.getEmail());
        patient.setHpid(patientDTO.getHpid());
        patient.setName(patientDTO.getName());
        patient.setGender(patientDTO.getGender());
        patient.setMobNo(patientDTO.getMobNo());

        Token token = new Token();
        token.setTokenNumber(String.valueOf(nextTokenNumber));
        token.setStatus(true);
        token.setPatient(patient);
        patient.setToken(token);
        patientRepository.save(patient);

        nextTokenNumber++; // Increment the token number for the next patient

        // Check if the day has changed
        LocalDate currentDay = LocalDate.now();
        if (previousDay == null || !previousDay.equals(currentDay)) {
            nextTokenNumber = 1; // Reset token number to 1 for the new day
        }
        previousDay = currentDay;
    }


    @Scheduled(cron = "0 0 * * *")
    public void schedule() {
        LocalDate date = LocalDate.now().minusDays(1);
        List<Token> list = tokenRepository.findByGenerationTime(date);

        for (int i = 0; i < list.size(); i++) {
            Token token = list.get(i);
            token.setStatus(false);
            list.set(i, token);
        }

        tokenRepository.saveAll(list);
    }
}





