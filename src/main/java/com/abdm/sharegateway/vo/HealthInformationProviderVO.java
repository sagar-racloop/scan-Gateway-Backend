package com.abdm.sharegateway.vo;

import com.abdm.sharegateway.domain.HealthInformationProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HealthInformationProviderVO {

    private String tokenUrl;
    private String searchUrl;
    private String mobileNumber;
    private String password;
    private Integer method;
    private String createPatientUrl;
    private byte[] imageByteArray;
    private Long dateCreated;
    private Long dateUpdate;

    public HealthInformationProviderVO(HealthInformationProvider hip) {
        this.tokenUrl = hip.getTokenUrl();
        this.searchUrl = hip.getSearchUrl();
        this.mobileNumber = hip.getMobileNumber();
        this.password = hip.getPassword();
        this.method = hip.getMethod();
        this.createPatientUrl = hip.getCreatePatientUrl();
        this.imageByteArray = hip.getImageByte();
        this.dateCreated = hip.getDateCreated().getTime();
        this.dateUpdate = hip.getDateUpdated().getTime();
    }
}
