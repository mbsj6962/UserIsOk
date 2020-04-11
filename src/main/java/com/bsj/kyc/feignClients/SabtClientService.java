package com.bsj.kyc.feignClients;

import com.bsj.kyc.hystrix.SabtServiceFallback;
import com.bsj.kyc.model.dto.SabtResultDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "sabt", url = "https://vosouq.free.beeceptor.com/sabt",
                fallback = SabtServiceFallback.class)
public interface SabtClientService {
    @RequestMapping(method = RequestMethod.POST,
            value = "/info",
            produces = "application/json")
    @ResponseBody
    SabtResultDto getInfo(@RequestParam("nationalCode") String nationalCode);
}
