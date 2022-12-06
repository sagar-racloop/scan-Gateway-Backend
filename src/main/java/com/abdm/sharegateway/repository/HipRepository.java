package com.abdm.sharegateway.repository;

import com.abdm.sharegateway.domain.HealthInformationProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HipRepository extends JpaRepository<HealthInformationProvider, Long> {

    Optional<HealthInformationProvider> findByHipIdAndCounterId(String hipId, String counterId);

}
