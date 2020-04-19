package com.bsj.kyc.service.impl;

import com.bsj.kyc.model.db.SelfDeclaredInfo;
import com.bsj.kyc.model.type.Status;
import com.bsj.kyc.service.SelfDeclaredService;
import com.bsj.kyc.utills.Address;
import com.bsj.kyc.utills.Utility;
import com.bsj.kyc.repository.SelfDeclaredRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;


@Transactional
@Service
public class SelfDeclaredServiceImpl implements SelfDeclaredService {

    @Autowired
    private SelfDeclaredRepository selfDeclaredRepository;

    @Override
    public SelfDeclaredInfo personalInfo(String name, String familyName, String nationalCode) {
        SelfDeclaredInfo selfDeclaredInfo = new SelfDeclaredInfo();
        selfDeclaredInfo.setName(name);
        selfDeclaredInfo.setFamilyName(familyName);
        selfDeclaredInfo.setNationalCode(nationalCode);
        return selfDeclaredRepository.save(selfDeclaredInfo);
    }

    @Override
    public SelfDeclaredInfo personalPicture(MultipartFile file, String nationalCode) throws IOException, NoSuchElementException {
        Utility.uploadFile(file, Utility.generateAddress(file,nationalCode, Address.CUSTOMER_PICTURE.getAddress()));
        SelfDeclaredInfo selfDeclaredInfo = selfDeclaredRepository.findByNationalCode(nationalCode).orElseThrow(NoSuchElementException::new);
        selfDeclaredInfo.setPictureAddress(Utility.generateAddress(file,nationalCode,Address.CUSTOMER_PICTURE.getAddress()));
        return selfDeclaredRepository.save(selfDeclaredInfo);
    }

    @Override
    public SelfDeclaredInfo personalVideo(MultipartFile file, String nationalCode) throws IOException, NoSuchElementException {
        Utility.uploadFile(file, Utility.generateAddress(file,nationalCode,Address.CUSTOMER_VIDEO.getAddress()));
        SelfDeclaredInfo selfDeclaredInfo = selfDeclaredRepository.findByNationalCode(nationalCode).orElseThrow(NoSuchElementException::new);
        selfDeclaredInfo.setVideoAddress(Utility.generateAddress(file,nationalCode,Address.CUSTOMER_VIDEO.getAddress()));
        selfDeclaredInfo.setStatus(Status.PENDING.getStatus());
        selfDeclaredInfo.setCreatedDate(Utility.getCurrentTime());
        return selfDeclaredRepository.save(selfDeclaredInfo);
    }

    @Override
    public List<SelfDeclaredInfo> getAllInfo()  {
        return selfDeclaredRepository.findAll();
    }
}
