package com.bsj.kyc.controller;

import com.bsj.kyc.model.db.SelfDeclaredInfo;
import com.bsj.kyc.model.dto.SelfDeclaredDto;
import com.bsj.kyc.service.SelfDeclaredService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/self-declared")
@Api(value = "/self-declared", description = "end points for getting data from your customer to see if he's valid or not for this service")
public class SelfDeclaredController {

    @Autowired
    private SelfDeclaredService selfDeclaredService;


    @PostMapping("/info")
    public ResponseEntity<SelfDeclaredInfo> personalInfo(@RequestBody SelfDeclaredDto selfDeclaredDto){
        return new ResponseEntity(selfDeclaredService.personalInfo(selfDeclaredDto),HttpStatus.OK);
    }

    @PostMapping(value = "/picture", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SelfDeclaredInfo> personalPicture(@RequestParam("file") MultipartFile file,
                                                            @RequestParam String nationalCode)
                                                            throws IOException, NoSuchFieldException {
        return new ResponseEntity(selfDeclaredService.personalPicture(file, nationalCode),HttpStatus.OK);
    }
    @PostMapping(value = "/video", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SelfDeclaredInfo> personalVideo(@RequestParam("file") MultipartFile file,
                                                          @RequestParam String nationalCode)
                                                            throws IOException, NoSuchFieldException {
        return new ResponseEntity(selfDeclaredService.personalVideo(file, nationalCode),HttpStatus.OK);
    }

    @GetMapping("/information")
    @ApiOperation("Gets all information of people who has registered and also for test ;)")
    private ResponseEntity<List<SelfDeclaredInfo>> getInformation() throws NoSuchElementException {
        return new ResponseEntity(selfDeclaredService.getAllInfo(), HttpStatus.OK);
    }
}
