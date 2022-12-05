package com.abdm.sharegateway.service.Impl;

import com.abdm.sharegateway.co.HealthInformationProviderCO;
import com.abdm.sharegateway.domain.HealthInformationProvider;
import com.abdm.sharegateway.exception.NoStackException;
import com.abdm.sharegateway.repository.HipRepository;
import com.abdm.sharegateway.service.HealthInformationProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthInformationProviderImpl implements HealthInformationProviderService {

    @Autowired
    private HipRepository hipRepository;

    @Override
    public Long save(HealthInformationProviderCO healthInformationProviderCO) {
        HealthInformationProvider healthInformationProvider = new HealthInformationProvider();
        healthInformationProvider.setHipId(healthInformationProviderCO.getHipId());
        healthInformationProvider.setCounterId(healthInformationProviderCO.getCounterId());
        healthInformationProvider.setTokenUrl(healthInformationProviderCO.getTokenUrl());
        healthInformationProvider.setServerUrl(healthInformationProviderCO.getServerUrl());
        healthInformationProvider = hipRepository.saveAndFlush(healthInformationProvider);
        return healthInformationProvider.getId();
    }

    @Override
    public List<HealthInformationProvider> getAll() {
        return hipRepository.findAll();
    }

    @Override
    public HealthInformationProvider get(Long id) {
        return hipRepository.findById(id).orElseThrow(() -> new NoStackException(
                "No entry found for provided id reference"));
    }

    @Override
    public HealthInformationProvider delete(Long id) {
        HealthInformationProvider provider = hipRepository.findById(id).orElseThrow(() -> new NoStackException(
                "No entry found for provided id reference"));
        provider.setDeleted(true);
        hipRepository.saveAndFlush(provider);
        return provider;
    }

    @Override
    public HealthInformationProvider update(Long id, HealthInformationProviderCO healthInformationProviderCO) {
        HealthInformationProvider healthInformationProvider = hipRepository.findById(id).orElseThrow(
                () -> new NoStackException("No entry found for provided id reference"));
        healthInformationProvider.setHipId(healthInformationProviderCO.getHipId());
        healthInformationProvider.setCounterId(healthInformationProviderCO.getCounterId());
        healthInformationProvider.setServerUrl(healthInformationProviderCO.getServerUrl());
        healthInformationProvider = hipRepository.saveAndFlush(healthInformationProvider);
        return healthInformationProvider;
    }
}
