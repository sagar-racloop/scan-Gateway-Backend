package com.abdm.sharegateway.co.validator.impl;

import com.abdm.sharegateway.co.HealthInformationProviderCO;
import com.abdm.sharegateway.co.validator.UrlValidator;
import com.abdm.sharegateway.exception.NoStackException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class UrlValidatorImpl implements ConstraintValidator<UrlValidator, HealthInformationProviderCO> {

    @Override
    public boolean isValid(HealthInformationProviderCO healthInformationProviderCO,
                           ConstraintValidatorContext constraintValidatorContext) {
        try {
            isValidUrl(healthInformationProviderCO.getServerUrl());
        } catch (MalformedURLException | URISyntaxException e) {
            throw new NoStackException("Invalid Server Url");
        }
        try {
            isValidUrl(healthInformationProviderCO.getTokenUrl());
        } catch (MalformedURLException | URISyntaxException e) {
            throw new NoStackException("Invalid Token Url");
        }
        return true;
    }

    private void isValidUrl(String url) throws URISyntaxException, MalformedURLException {

        new URL(url).toURI();
    }

}
