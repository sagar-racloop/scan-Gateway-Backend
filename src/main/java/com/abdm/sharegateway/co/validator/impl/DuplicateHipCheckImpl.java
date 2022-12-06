package com.abdm.sharegateway.co.validator.impl;

import com.abdm.sharegateway.co.HipLogoCo;
import com.abdm.sharegateway.co.validator.DuplicateHipCheck;
import com.abdm.sharegateway.service.HipLogoService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class DuplicateHipCheckImpl implements ConstraintValidator<DuplicateHipCheck, HipLogoCo> {

    @Autowired
    private HipLogoService hipLogoService;

    @Override
    public boolean isValid(HipLogoCo hipLogoCo, ConstraintValidatorContext context) {
        return hipLogoService.isHipExist(hipLogoCo.getHipId());
    }
}
