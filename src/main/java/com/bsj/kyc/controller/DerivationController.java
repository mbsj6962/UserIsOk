package com.bsj.kyc.controller;

import com.bsj.kyc.model.dto.SabtDto;
import com.bsj.kyc.service.DerivationService;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/derivation")
@Api(value = "/derivation", description = "Operations about derivation of your customer to know if they are valid to take advantage of your service")
public class DerivationController {

    private final DerivationService derivationService;

    public DerivationController(DerivationService derivationService) {
        this.derivationService = derivationService;
    }

    @GetMapping("/{nationalCode}")
    public SabtDto isConfirmed(@PathVariable String nationalCode){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(derivationService.isConfirmed(nationalCode), SabtDto.class);
    }


}
