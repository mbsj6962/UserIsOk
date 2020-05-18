package com.bsj.kyc.controller;

import com.bsj.kyc.model.dto.SabtDto;
import com.bsj.kyc.service.DerivationService;
import com.bsj.kyc.service.SelfDeclaredService;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/derivation")
@Api(value = "/derivation", description = "Operations about derivation of your customer to know if they are valid to take advantage of your service")
public class DerivationController extends AbstractController {

    private final DerivationService derivationService;
    private final SelfDeclaredService selfDeclaredService;
    public DerivationController(DerivationService derivationService, SelfDeclaredService selfDeclaredService) {
        this.derivationService = derivationService;
        this.selfDeclaredService = selfDeclaredService;
    }

    @GetMapping("/{nationalCode}")
    public SabtDto isConfirmed(@PathVariable String nationalCode){
        checkResourceFound(selfDeclaredService.getInfo(nationalCode));
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(derivationService.isConfirmed(nationalCode), SabtDto.class);
    }


}
