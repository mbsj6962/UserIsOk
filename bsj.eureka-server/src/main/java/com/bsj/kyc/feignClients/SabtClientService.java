package com.bsj.kyc.feignClients;

import com.bsj.kyc.model.dto.SabtResultDto;
import com.bsj.kyc.hystrix.SabtServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "sabt", url = "https://vosouq.free.beeceptor.com/sabt/",
                fallback = SabtServiceFallback.class)
public interface SabtClientService {
    @PostMapping(value = "/info")
    SabtResultDto getInfo(@RequestParam String nationalCode);
}
