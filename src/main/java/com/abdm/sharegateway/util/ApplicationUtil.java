package com.abdm.sharegateway.util;

import com.abdm.sharegateway.co.HealthInformationProviderCO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApplicationUtil {

    public static HealthInformationProviderCO getHipJsonObject(String jsonString) {
        HealthInformationProviderCO healthInformationProviderCO;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            healthInformationProviderCO = objectMapper.readValue(jsonString, HealthInformationProviderCO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return healthInformationProviderCO;
    }
}
