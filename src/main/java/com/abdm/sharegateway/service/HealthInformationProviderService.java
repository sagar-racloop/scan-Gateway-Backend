package com.abdm.sharegateway.service;

import com.abdm.sharegateway.co.HealthInformationProviderCO;
import com.abdm.sharegateway.domain.HealthInformationProvider;
import com.abdm.sharegateway.vo.HealthInformationProviderDetailedVO;
import com.abdm.sharegateway.vo.HealthInformationProviderVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HealthInformationProviderService {

    Long save(String healthInformationProviderCO, MultipartFile multipartFile);

    List<HealthInformationProviderVO> getAll();

    HealthInformationProvider get(Long id);

    HealthInformationProvider delete(Long id);

    HealthInformationProvider update(Long id, HealthInformationProviderCO healthInformationProviderCO);

    HealthInformationProviderDetailedVO getForHipAndCounter(String hipId, String counterId);
}
