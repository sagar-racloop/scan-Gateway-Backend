package com.abdm.sharegateway.co;

import com.abdm.sharegateway.co.validator.UrlValidator;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@UrlValidator
public class HealthInformationProviderCO {

    @NotNull
    private String hipId;

    @NotNull(message = "Counter Id is required field")
    private String counterId;

    @NotNull()
    private String tokenUrl;

    @NotNull
    private String serverUrl;

}
