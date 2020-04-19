package com.bsj.kyc.service;

import com.bsj.kyc.model.db.SelfDeclaredInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SelfDeclaredService {
    SelfDeclaredInfo personalInfo(String name, String familyName, String nationalCode);
    SelfDeclaredInfo personalPicture(MultipartFile file, String nationalCode) throws IOException, NoSuchFieldException;
    SelfDeclaredInfo personalVideo(MultipartFile file, String nationalCode) throws IOException, NoSuchFieldException;
    List<SelfDeclaredInfo> getAllInfo() ;
}
