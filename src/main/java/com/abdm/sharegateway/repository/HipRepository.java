package com.abdm.sharegateway.repository;

import com.abdm.sharegateway.domain.HealthInformationProvider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HipRepository extends JpaRepository<HealthInformationProvider, Long> {
}
