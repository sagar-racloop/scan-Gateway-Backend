package com.abdm.sharegateway.co.validator.impl;

import com.abdm.sharegateway.co.validator.ValidImage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

public class ImageValidator implements ConstraintValidator<ValidImage, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {

        boolean result = true;

        if (multipartFile != null) {
            String contentType = multipartFile.getContentType();
            assert contentType != null;
            if (!isSupportedContentType(contentType)) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(
                                "Only PNG or JPG images are allowed.")
                        .addConstraintViolation();

                result = false;
            }
        }
        return result;
    }

    private boolean isSupportedContentType(String contentType) {
        return contentType.equals("image/png")
                || contentType.equals("image/jpg")
                || contentType.equals("image/jpeg");
    }
}
