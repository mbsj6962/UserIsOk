package com.bsj.kyc.service;

import com.bsj.kyc.model.db.SelfDeclaredInfo;
import com.bsj.kyc.model.dto.SelfDeclaredDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SelfDeclaredService {
    SelfDeclaredInfo personalInfo(SelfDeclaredDto selfDeclaredDto);
    SelfDeclaredInfo personalPicture(MultipartFile file, String nationalCode) throws IOException, NoSuchFieldException;
    SelfDeclaredInfo personalVideo(MultipartFile file, String nationalCode) throws IOException, NoSuchFieldException;
    List<SelfDeclaredInfo> getAllInfo() ;
}
