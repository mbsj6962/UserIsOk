package com.bsj.kyc.service.impl;

import com.bsj.kyc.feignClients.SabtClientService;
import com.bsj.kyc.model.db.SabtInfo;
import com.bsj.kyc.model.dto.SabtResultDto;
import com.bsj.kyc.repository.SabtInfoRepository;
import com.bsj.kyc.service.SabtInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;

/*** @Author By MohamadBiiij@gmail.com ***/

@Service
public class SabtInfoServiceImpl implements SabtInfoService {

    Logger logger = LoggerFactory.getLogger(SabtInfoServiceImpl.class);


    @Autowired
    private SabtInfoRepository sbtInfoRepository;

    final SabtClientService sabtClientService;

    @Autowired
    public SabtInfoServiceImpl(SabtClientService sabtClientService) {
        this.sabtClientService = sabtClientService;
    }

    @Override
    public SabtInfo fetchInfo(String nationalCode) throws ParseException {
        SabtResultDto sabtResultDto = sabtClientService.getInfo(nationalCode);
        SabtInfo sabtInfo = new SabtInfo();
        sabtInfo.setName(sabtResultDto.getName());
        sabtInfo.setFamilyName(sabtResultDto.getFamilyName());
        sabtInfo.setFatherName(sabtResultDto.getFatherName());
        sabtInfo.setLivingStatus(sabtResultDto.getLivingStatus());
        sabtInfo.setIdentityNumber(sabtResultDto.getIdentityNumber());
        sabtInfo.setIdentitySerie(sabtResultDto.getIdentitySerie());
        sabtInfo.setIdentityStatus(sabtResultDto.getIdentityStatus());
        sabtInfo.setPostalCode(sabtResultDto.getPostalCode());
        sabtInfo.setBirthdate(sabtResultDto.convertDate(sabtResultDto.getBirthdate()));
        sabtInfo.setPicture(sabtResultDto.getPicture());
        return sbtInfoRepository.save(sabtInfo);
    }
}
