package com.bsj.kyc.service;

import com.bsj.kyc.model.db.SelfDeclaredInfo;

import java.text.ParseException;

public interface DerivationService  {

    SelfDeclaredInfo isValid() throws  ParseException;
    SelfDeclaredInfo isConfirmed(String nationalCode);
}
