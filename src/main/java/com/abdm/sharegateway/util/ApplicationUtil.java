package com.abdm.sharegateway.util;

import com.abdm.sharegateway.co.HipLogoCo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApplicationUtil {

    public static HipLogoCo getJson(String jsonString) {
        HipLogoCo hipLogoCo;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            hipLogoCo = objectMapper.readValue(jsonString, HipLogoCo.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return hipLogoCo;
    }
}
