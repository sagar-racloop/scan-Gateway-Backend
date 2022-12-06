package com.abdm.sharegateway.service.Impl;

import com.abdm.sharegateway.co.HealthInformationProviderCO;
import com.abdm.sharegateway.domain.HealthInformationProvider;
import com.abdm.sharegateway.exception.NoStackException;
import com.abdm.sharegateway.repository.HipRepository;
import com.abdm.sharegateway.service.HealthInformationProviderService;
import com.abdm.sharegateway.util.ApplicationUtil;
import com.abdm.sharegateway.vo.HealthInformationProviderDetailedVO;
import com.abdm.sharegateway.vo.HealthInformationProviderVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HealthInformationProviderImpl implements HealthInformationProviderService {

    @Autowired
    private HipRepository hipRepository;

    @Override
    public Long save(String hipString, MultipartFile multipartFile) {
        HealthInformationProviderCO healthInformationProviderCO = ApplicationUtil.getHipJsonObject(hipString);
        HealthInformationProvider healthInformationProvider = new HealthInformationProvider();
//        healthInformationProviderCO.setImageFile(multipartFile);
        BeanUtils.copyProperties(healthInformationProviderCO, healthInformationProvider);
        try {
            healthInformationProvider.setImageByte(multipartFile.getBytes());
        } catch (IOException e) {
            throw new NoStackException("Error in saving file.");
        }
//        healthInformationProvider.setHipId(healthInformationProviderCO.getHipId());
//        healthInformationProvider.setCounterId(healthInformationProviderCO.getCounterId());
//        healthInformationProvider.setTokenUrl(healthInformationProviderCO.getTokenUrl());
//        healthInformationProvider.setSearchUrl(healthInformationProviderCO.getSearchUrl());
//        healthInformationProvider.setMethod(healthInformationProviderCO.getMethod());
//        healthInformationProvider.setMobileNumber(healthInformationProviderCO.getMobileNumber());
//        healthInformationProvider.setPassword(healthInformationProviderCO.getPassword());

        //        healthInformationProvider.setCreatePatientUrl(healthInformationProviderCO.getCreatePatientUrl());
        healthInformationProvider = hipRepository.saveAndFlush(healthInformationProvider);
        return healthInformationProvider.getId();
    }

    @Override
    public List<HealthInformationProviderVO> getAll() {
        return hipRepository.findAll().stream().map(HealthInformationProviderVO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
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
        BeanUtils.copyProperties(healthInformationProviderCO, healthInformationProvider);
//        healthInformationProvider.setHipId(healthInformationProviderCO.getHipId());
//        healthInformationProvider.setCounterId(healthInformationProviderCO.getCounterId());
//        healthInformationProvider.setSearchUrl(healthInformationProviderCO.getSearchUrl());
//        healthInformationProvider.setMethod(healthInformationProviderCO.getMethod());
//        healthInformationProvider.setMobileNumber(healthInformationProviderCO.getMobileNumber());
//        healthInformationProvider.setPassword(healthInformationProviderCO.getPassword());
//        healthInformationProvider.setCreatePatientUrl(healthInformationProviderCO.getCreatePatientUrl());
        healthInformationProvider = hipRepository.saveAndFlush(healthInformationProvider);
        return healthInformationProvider;
    }

    @Override
    @Transactional
    public HealthInformationProviderDetailedVO getForHipAndCounter(String hipId, String counterId) {
        HealthInformationProvider healthInformationProvider = hipRepository.findByHipIdAndCounterId(hipId, counterId).orElseThrow(
                () -> new NoStackException(""));
        return new HealthInformationProviderDetailedVO(healthInformationProvider);
    }
}
