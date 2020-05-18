package com.bsj.kyc.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor

public class HTTP404Exception extends RuntimeException{

    public HTTP404Exception(String message){
        super(message);
    }
    public HTTP404Exception(Throwable cause){
        super(cause);
    }
}
