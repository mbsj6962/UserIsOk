package com.bsj.kyc.service;

import com.bsj.kyc.model.db.SabtFetchedInfo;

import java.text.ParseException;

public interface SabtInfoService {
        SabtFetchedInfo fetchInfo(String nationalCode) throws ParseException;
}