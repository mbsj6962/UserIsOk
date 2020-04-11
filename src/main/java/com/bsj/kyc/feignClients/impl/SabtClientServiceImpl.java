//package com.vsq.kyc.feignClients.impl;
//
//import com.vsq.kyc.feignClients.SabtClientService;
//import com.vsq.kyc.model.dto.SabtResultDto;
//import feign.Feign;
//import feign.Logger;
//import feign.gson.GsonDecoder;
//import feign.gson.GsonEncoder;
//import feign.okhttp.OkHttpClient;
//import feign.slf4j.Slf4jLogger;
//import okhttp3.Interceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class SabtClientServiceImpl  {
//
//    private SabtResultDto resultClient = Feign.builder()
//             .client(new OkHttpClient())
//             .encoder(new GsonEncoder())
//             .decoder(new GsonDecoder())
//             .logger(new Slf4jLogger(SabtClientService.class))
//             .logLevel(Logger.Level.FULL)
//             .target(SabtResultDto.class, "https://vosouq.free.beeceptor.com/sabt/info");
//
//          @Autowired
//           private SabtClientService sabtClientService;
//
//          public SabtResultDto getInfo(String nationalCode){
//               return sabtClientService.getInfo(nationalCode);
//          }
//
//}
