package com.abdm.sharegateway.service.Impl;

import com.abdm.sharegateway.co.HealthInformationProviderCO;
import com.abdm.sharegateway.domain.HealthInformationProvider;
import com.abdm.sharegateway.exception.NoStackException;
import com.abdm.sharegateway.repository.HipRepository;
import com.abdm.sharegateway.service.HealthInformationProviderService;
import com.abdm.sharegateway.util.ApplicationUtil;
import com.abdm.sharegateway.vo.HealthInformationProviderDetailedVO;
import com.abdm.sharegateway.vo.HealthInformationProviderSelectListVO;
import com.abdm.sharegateway.vo.HealthInformationProviderVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HealthInformationProviderImpl implements HealthInformationProviderService {

    @Autowired
    private HipRepository hipRepository;

    @Override
    public Long save(String hipString, MultipartFile multipartFile) {
        HealthInformationProviderCO healthInformationProviderCO = ApplicationUtil.getHipJsonObject(hipString);
        if (hipRepository.existsByHipIdAndCounterId(healthInformationProviderCO.getHipId(), healthInformationProviderCO.getCounterId())){
            throw new NoStackException("This pair of combination of HID and counterId already exists.");
        }
        HealthInformationProvider healthInformationProvider = new HealthInformationProvider();
        BeanUtils.copyProperties(healthInformationProviderCO, healthInformationProvider);
        try {
            healthInformationProvider.setImageByte(multipartFile.getBytes());
        } catch (IOException e) {
            throw new NoStackException("Error in saving file.");
        }
        healthInformationProvider = hipRepository.saveAndFlush(healthInformationProvider);
        return healthInformationProvider.getId();
    }

    @Override
    public List<HealthInformationProviderVO> getAll() {
        return hipRepository.findAll().stream().map(HealthInformationProviderDetailedVO::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public HealthInformationProviderDetailedVO get(String hipId, String counterId) {
        HealthInformationProvider provider = hipRepository.findByHipIdAndCounterId(hipId, counterId).orElseThrow(() -> new NoStackException(
                "No entry found for provided id reference"));
        return new HealthInformationProviderDetailedVO(provider);
    }

    @Override
    @Transactional
    public HealthInformationProvider delete(String hipId, String counterId) {
        HealthInformationProvider provider = hipRepository.findByHipIdAndCounterId(hipId, counterId).orElseThrow(() -> new NoStackException(
                "No entry found for provided id reference"));
        provider.setDeleted(true);
        provider.setHipId(provider.getHipId().concat(String.valueOf(new Date().getTime())));
        provider = hipRepository.saveAndFlush(provider);
        return provider;
    }

    @Override
    @Transactional
    public HealthInformationProvider update(String id, String counterId, String hipInString, MultipartFile multipartFile) {
        HealthInformationProviderCO healthInformationProviderCO = ApplicationUtil.getHipJsonObject(hipInString);
        HealthInformationProvider healthInformationProvider = hipRepository.findByHipIdAndCounterId(id, counterId).orElseThrow(
                () -> new NoStackException("No entry found for provided id reference"));
        healthInformationProviderCO.setHipId(id);
        healthInformationProviderCO.setCounterId(counterId);
        BeanUtils.copyProperties(healthInformationProviderCO, healthInformationProvider);
        try {
            healthInformationProvider.setImageByte(multipartFile.getBytes());
        } catch (IOException e) {
            throw new NoStackException("Error in saving file.");
        }
        healthInformationProvider = hipRepository.saveAndFlush(healthInformationProvider);
        return healthInformationProvider;
    }

    @Override
    public HealthInformationProviderSelectListVO selectList() {
        List<HealthInformationProvider> providers = hipRepository.findAll();
        List<String> tokenList = providers.stream().map(HealthInformationProvider::getTokenUrl).collect(Collectors.toList());
        List<String> searchList = providers.stream().map(HealthInformationProvider::getSearchUrl).collect(Collectors.toList());
        List<String> patientList = providers.stream().map(HealthInformationProvider::getCreatePatientUrl).collect(Collectors.toList());
        List<Integer> methodList = providers.stream().map(HealthInformationProvider::getMethod).collect(Collectors.toList());
        return new HealthInformationProviderSelectListVO(methodList, tokenList, searchList, patientList);
    }
}
