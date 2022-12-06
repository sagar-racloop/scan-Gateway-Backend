package com.abdm.sharegateway.co;

import com.abdm.sharegateway.co.validator.UrlValidator;
import com.abdm.sharegateway.co.validator.ValidImage;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@UrlValidator
public class HealthInformationProviderCO {

    @NotNull
    private String hipId;

    @NotNull(message = "Counter Id is required field")
    private String counterId;

    @NotNull
    private String tokenUrl;

    @NotNull
    private String searchUrl;

    @NotNull
    private String mobileNumber;

    @NotNull
    private String password;

    @NotNull
    private Integer method;

    @NotNull
    private String createPatientUrl;

    @ValidImage
    private MultipartFile imageFile;
}
