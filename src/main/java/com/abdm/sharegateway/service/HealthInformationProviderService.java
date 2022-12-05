package com.abdm.sharegateway.service;

import com.abdm.sharegateway.co.HealthInformationProviderCO;
import com.abdm.sharegateway.domain.HealthInformationProvider;

import java.util.List;

public interface HealthInformationProviderService {

    Long save(HealthInformationProviderCO healthInformationProviderCO);

    List<HealthInformationProvider> getAll();

    HealthInformationProvider get(Long id);

    HealthInformationProvider delete(Long id);

    HealthInformationProvider update(Long id, HealthInformationProviderCO healthInformationProviderCO);
}
