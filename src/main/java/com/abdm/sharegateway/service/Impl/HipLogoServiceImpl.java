package com.abdm.sharegateway.service.Impl;

import com.abdm.sharegateway.co.HipLogoCo;
import com.abdm.sharegateway.domain.HipLogo;
import com.abdm.sharegateway.exception.NoStackException;
import com.abdm.sharegateway.repository.LogoRepository;
import com.abdm.sharegateway.service.HipLogoService;
import com.abdm.sharegateway.util.ApplicationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class HipLogoServiceImpl implements HipLogoService {

    @Autowired
    private LogoRepository logoRepository;

    @Override
    public Long save(String hipLogoCoString, MultipartFile file) {
        HipLogoCo hipLogoCo = ApplicationUtil.getJson(hipLogoCoString);
        HipLogo hipLogo = new HipLogo();
        hipLogo.setHipId(hipLogoCo.getHipId());
        hipLogo.setMethod(hipLogoCo.getMethod());
        try {
            hipLogo.setImageByte(file.getBytes());
        } catch (IOException e) {
            throw new NoStackException("Issue in reading file.");
        }
        hipLogo = logoRepository.saveAndFlush(hipLogo);
        return hipLogo.getId();
    }

    @Override
    public HipLogo get(Long id) {
        return logoRepository.findById(id).orElseThrow(() ->
                new NoStackException("No Result found for this id reference"));
    }

    @Override
    public List<HipLogo> getAll() {
        return logoRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        logoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public HipLogo findByHipId(String hipId) {
        return logoRepository.findByHipId(hipId).orElseThrow(() ->
                new NoStackException("No record found against provided HIP id."));
    }

    @Override
    public boolean isHipExist(String hipId) {
        return (logoRepository.countByHipId(hipId) > 1);
    }

    @Override
    @Transactional
    public void updateLogoForHip(String hipId, MultipartFile imageFile) {
        HipLogo hipLogo = findByHipId(hipId);
        try {
            hipLogo.setImageByte(imageFile.getBytes());
        } catch (IOException e) {
            throw new NoStackException("Issue in reading file.");
        }
        logoRepository.saveAndFlush(hipLogo);
    }
}
