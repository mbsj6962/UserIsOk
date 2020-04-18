package com.bsj.kyc.controller;

import com.bsj.kyc.service.DerivationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/derivation")
@Api(value = "/derivation", description = "Operations about derivation of your customer to know if they are valid to take advantage of your service")
public class DerivationController {

    @Autowired
    private DerivationService derivationService;
//    @PostMapping("/valid")
//    public ResponseEntity<?> isValidPerson() throws ParseException {
//        SelfDeclaredInfo selfDeclaredInfo = derivationService.isValid();
//        return ResponseEntity.ok().body("this person is valid with nationalCode : " + selfDeclaredInfo.getNationalCode());
//    }

    @GetMapping("/{nationalCode}")
    public ResponseEntity<?> isConfirmed(@PathVariable("nationalCode") String nationalCode){

        return new ResponseEntity(derivationService.isConfirmed(nationalCode), HttpStatus.OK);
    }


}
