package com.bsj.kyc.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HTTP400Exception extends RuntimeException{

    public HTTP400Exception(String message){
        super(message);
    }
    public HTTP400Exception(Throwable cause){
        super(cause);
    }

}
