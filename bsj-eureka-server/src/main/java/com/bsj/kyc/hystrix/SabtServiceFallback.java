package com.bsj.kyc.hystrix;

import com.bsj.kyc.feignClients.SabtClientService;
import com.bsj.kyc.model.dto.SabtResultDto;
import org.springframework.stereotype.Component;

@Component
public class SabtServiceFallback implements SabtClientService {
    @Override
    public SabtResultDto getInfo(String nationalCode) {
        return null;
    }
}
