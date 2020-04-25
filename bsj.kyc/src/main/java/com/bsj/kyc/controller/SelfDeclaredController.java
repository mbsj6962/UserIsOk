package com.bsj.kyc.controller;

import com.bsj.kyc.model.db.SelfDeclaredInfo;
import com.bsj.kyc.events.SelfDeclaredInfoEvent;
import com.bsj.kyc.model.dto.SelfDeclaredDto;
import com.bsj.kyc.service.SelfDeclaredService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;
@RestController
@RequestMapping("/api/self-declared")
@Api(value = "/self-declared", description = "end points for getting data from your customer to see if he's valid or not for this service")
public class SelfDeclaredController extends AbstractController{

    private final SelfDeclaredService selfDeclaredService;

    public SelfDeclaredController(SelfDeclaredService selfDeclaredService) {
        this.selfDeclaredService = selfDeclaredService;
    }

    @PostMapping("/info")
    public SelfDeclaredInfo personalInfo(@RequestBody SelfDeclaredDto selfDeclaredDto){
        SelfDeclaredInfo selfDeclaredInfo = selfDeclaredService.personalInfo(selfDeclaredDto.getName(),
                selfDeclaredDto.getFamilyName(), selfDeclaredDto.getNationalCode());

        SelfDeclaredInfoEvent event =
                new SelfDeclaredInfoEvent("one person has been added : " , selfDeclaredInfo);
        eventPublisher.publishEvent(event);
        return selfDeclaredInfo;
    }

    @PostMapping(value = "/picture", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SelfDeclaredInfo> personalPicture(@RequestParam("file") MultipartFile file,
                                                            @RequestParam String nationalCode)
                                                            throws IOException, NoSuchFieldException {
        checkResourceFound(selfDeclaredService.getInfo(nationalCode));
        return new ResponseEntity(selfDeclaredService.personalPicture(file, nationalCode),HttpStatus.OK);
    }
    @PostMapping(value = "/video", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SelfDeclaredInfo> personalVideo(@RequestParam("file") MultipartFile file,
                                                          @RequestParam String nationalCode)
                                                            throws IOException, NoSuchFieldException {
        checkResourceFound(selfDeclaredService.getInfo(nationalCode));
        SelfDeclaredInfo selfDeclaredInfo = selfDeclaredService.personalVideo(file, nationalCode);
        SelfDeclaredInfoEvent event =
                new SelfDeclaredInfoEvent("one person has been added successfully : " , selfDeclaredInfo);
        eventPublisher.publishEvent(event);
        return new ResponseEntity(selfDeclaredInfo , HttpStatus.OK);
    }

    @GetMapping("/information")
    @ApiOperation("Gets all information of people who has registered and also for test ;)")
    public ResponseEntity<List<SelfDeclaredInfo>> getInformation(){
        return new ResponseEntity(selfDeclaredService.getAllInfo(), HttpStatus.OK);
    }
}
