package com.abdm.sharegateway.vo;

import com.abdm.sharegateway.domain.HealthInformationProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HealthInformationProviderDetailedVO extends HealthInformationProviderVO {

    private String hipId;
    private String counterId;

    public HealthInformationProviderDetailedVO(HealthInformationProvider hip) {
        super(hip);
        this.hipId = hip.getHipId();
        this.counterId = hip.getCounterId();
    }
}
