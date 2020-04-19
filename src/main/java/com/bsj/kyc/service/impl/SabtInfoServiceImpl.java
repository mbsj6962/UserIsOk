package com.bsj.kyc.service.impl;

import com.bsj.kyc.feignClients.SabtClientService;
import com.bsj.kyc.model.db.SabtFetchedInfo;
import com.bsj.kyc.model.dto.SabtResultDto;
import com.bsj.kyc.repository.SabtFetchedInfoRepository;
import com.bsj.kyc.service.SabtInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;

@Transactional
@Service
public class SabtInfoServiceImpl implements SabtInfoService {

    private Logger logger = LoggerFactory.getLogger(SabtInfoServiceImpl.class);


    private final SabtFetchedInfoRepository sbtInfoRepository;

    private final SabtClientService sabtClientService;

    public SabtInfoServiceImpl(SabtFetchedInfoRepository sbtInfoRepository, SabtClientService sabtClientService) {
        this.sbtInfoRepository = sbtInfoRepository;
        this.sabtClientService = sabtClientService;
    }

    @Override
    public SabtFetchedInfo fetchInfo(String nationalCode) throws ParseException {
        SabtResultDto sabtResultDto = sabtClientService.getInfo(nationalCode);
        SabtFetchedInfo sabtInfo = new SabtFetchedInfo();
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
