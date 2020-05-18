package com.bsj.kyc.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
public class RestAPIExceptionInfo {
    private final String message;
    private final String detail;

    public RestAPIExceptionInfo(){
        message=null;
        detail=null;
    }

}
