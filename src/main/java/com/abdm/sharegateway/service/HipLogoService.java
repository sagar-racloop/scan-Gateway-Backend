package com.abdm.sharegateway.service;

import com.abdm.sharegateway.domain.HipLogo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HipLogoService {

    Long save(String hipLogoCo, MultipartFile file);

    HipLogo get(Long id);

    List<HipLogo> getAll();

    void delete(Long id);

    HipLogo findByHipId(String hipId);

    boolean isHipExist(String hipId);

    void updateLogoForHip(String hipId, MultipartFile imageFile);
}
