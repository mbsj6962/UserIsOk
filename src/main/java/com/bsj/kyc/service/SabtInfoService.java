package com.bsj.kyc.service;

import com.bsj.kyc.model.db.SabtInfo;
import java.text.ParseException;

public interface SabtInfoService {
        SabtInfo fetchInfo(String nationalCode) throws ParseException;
}