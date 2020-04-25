package com.bsj.kyc.service.impl;

import com.bsj.kyc.model.db.SelfDeclaredInfo;
import com.bsj.kyc.model.type.Status;
import com.bsj.kyc.repository.SelfDeclaredRepository;
import com.bsj.kyc.service.SelfDeclaredService;
import com.bsj.kyc.utills.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import static com.bsj.kyc.utills.Address.CUSTOMER_PICTURE;
import static com.bsj.kyc.utills.Address.CUSTOMER_VIDEO;
import static com.bsj.kyc.utills.Utility.uploadFile;


@Transactional
@Service
public class SelfDeclaredServiceImpl implements SelfDeclaredService {

    private final static Logger logger = LoggerFactory.getLogger(SelfDeclaredServiceImpl.class);

    private final SelfDeclaredRepository selfDeclaredRepository;

    public SelfDeclaredServiceImpl(SelfDeclaredRepository selfDeclaredRepository) {
        this.selfDeclaredRepository = selfDeclaredRepository;
    }

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
        uploadFile(file, Utility.generateAddress(file,nationalCode, CUSTOMER_PICTURE.getAddress()));
        SelfDeclaredInfo selfDeclaredInfo = selfDeclaredRepository.findByNationalCode(nationalCode).orElseThrow(NoSuchElementException::new);
        selfDeclaredInfo.setPictureAddress(Utility.generateAddress(file,nationalCode, CUSTOMER_PICTURE.getAddress()));
        return selfDeclaredRepository.save(selfDeclaredInfo);
    }

    @Override
    public SelfDeclaredInfo personalVideo(MultipartFile file, String nationalCode) throws IOException, NoSuchElementException {
        uploadFile(file, Utility.generateAddress(file,nationalCode, CUSTOMER_VIDEO.getAddress()));
        SelfDeclaredInfo selfDeclaredInfo = selfDeclaredRepository.findByNationalCode(nationalCode).orElseThrow(NoSuchElementException::new);
        selfDeclaredInfo.setVideoAddress(Utility.generateAddress(file,nationalCode,CUSTOMER_VIDEO.getAddress()));
        selfDeclaredInfo.setStatus(Status.PENDING.getStatus());
        selfDeclaredInfo.setCreatedDate(Utility.getCurrentTime());
        return selfDeclaredRepository.save(selfDeclaredInfo);
    }

    @Override
    public List<SelfDeclaredInfo> getAllInfo()  {
        return selfDeclaredRepository.findAll();
    }

    @Override
    public SelfDeclaredInfo getInfo(String nationalCode) {
        return selfDeclaredRepository.findByNationalCode(nationalCode).orElseThrow(NoSuchElementException::new);
    }
}
