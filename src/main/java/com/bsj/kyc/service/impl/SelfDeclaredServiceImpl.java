package com.bsj.kyc.service.impl;

import com.bsj.kyc.repository.SelfDeclaredRepository;
import com.bsj.kyc.service.SelfDeclaredService;
import com.bsj.kyc.utills.Utillity;
import com.bsj.kyc.model.db.SelfDeclaredInfo;
import com.bsj.kyc.model.dto.SelfDeclaredDto;
import com.bsj.kyc.model.type.Status;
import com.bsj.kyc.utills.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SelfDeclaredServiceImpl implements SelfDeclaredService {

    @Autowired
    private SelfDeclaredRepository selfDeclaredRepository;

    @Override
    public SelfDeclaredInfo personalInfo(SelfDeclaredDto selfDeclaredDto) {
        SelfDeclaredInfo selfDeclaredInfo = new SelfDeclaredInfo();
        selfDeclaredInfo.setName(selfDeclaredDto.getName());
        selfDeclaredInfo.setFamilyName(selfDeclaredDto.getFamilyName());
        selfDeclaredInfo.setNationalCode(selfDeclaredDto.getNationalCode());
        return selfDeclaredRepository.save(selfDeclaredInfo);
    }

    @Override
    public SelfDeclaredInfo personalPicture(MultipartFile file, String nationalCode) throws IOException, NoSuchElementException {
        Utillity.uploadFile(file, Utillity.generateAddress(file,nationalCode,Address.CUSTOMER_PICTURE.getAddress()));
        SelfDeclaredInfo selfDeclaredInfo = selfDeclaredRepository.findByNationalCode(nationalCode).orElseThrow(NoSuchElementException::new);
        selfDeclaredInfo.setPictureAddress(Utillity.generateAddress(file,nationalCode,Address.CUSTOMER_PICTURE.getAddress()));
        return selfDeclaredRepository.save(selfDeclaredInfo);
    }

    @Override
    public SelfDeclaredInfo personalVideo(MultipartFile file, String nationalCode) throws IOException, NoSuchElementException {
        Utillity.uploadFile(file, Utillity.generateAddress(file,nationalCode,Address.CUSTOMER_VIDEO.getAddress()));
        SelfDeclaredInfo selfDeclaredInfo = selfDeclaredRepository.findByNationalCode(nationalCode).orElseThrow(NoSuchElementException::new);
        selfDeclaredInfo.setVideoAddress(Utillity.generateAddress(file,nationalCode,Address.CUSTOMER_VIDEO.getAddress()));
        selfDeclaredInfo.setStatus(Status.PENDING.getStatus());
        selfDeclaredInfo.setCreatedDate(Utillity.getCurrentTime());
        SelfDeclaredInfo selfDeclaredInfo1 = selfDeclaredRepository.save(selfDeclaredInfo);
        return selfDeclaredInfo1;
    }

    @Override
    public List<SelfDeclaredInfo> getAllInfo()  {
        return selfDeclaredRepository.findAll();
    }
}
