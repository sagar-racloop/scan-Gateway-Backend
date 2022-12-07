package com.abdm.sharegateway.service;

import com.abdm.sharegateway.domain.HealthInformationProvider;
import com.abdm.sharegateway.vo.HealthInformationProviderDetailedVO;
import com.abdm.sharegateway.vo.HealthInformationProviderVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HealthInformationProviderService {

    Long save(String healthInformationProviderCO, MultipartFile multipartFile);

    List<HealthInformationProviderVO> getAll();

    HealthInformationProviderDetailedVO get(String id, String counterId);

    HealthInformationProvider delete(String hipId, String counterId);

    HealthInformationProvider update(String id, String counterId, String healthInformationProviderCO, MultipartFile multipartFile);

}
