package com.abdm.sharegateway.co;

import com.abdm.sharegateway.co.validator.DuplicateHipCheck;
import com.abdm.sharegateway.co.validator.ValidImage;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@DuplicateHipCheck
public class HipLogoCo {

    @NotNull
    private String hipId;

    @NotNull
    private Integer method;

    @NotNull
    @ValidImage
    private MultipartFile imageFile;
}
