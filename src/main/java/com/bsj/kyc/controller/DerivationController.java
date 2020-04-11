//package com.vsq.kyc.controller;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.vsq.kyc.service.DerivationService;
//import io.swagger.annotations.Api;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.text.ParseException;
//
//@RestController
//@RequestMapping("/api/derivation")
//@Api(value = "/derivation", description = "Operations about derivation of your customer to know if they are valid to take advantage of your service")
//public class DerivationController {
//
//
//
//    @Autowired
//    private DerivationService derivationService;
//    @PostMapping("/valid")
//    public void isValidPerson() throws JsonProcessingException, ParseException {
//        derivationService.isValid();
//    }
//
//
//}
