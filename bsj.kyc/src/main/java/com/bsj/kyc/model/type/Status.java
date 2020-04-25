package com.bsj.kyc.model.type;

import lombok.*;

@Getter
@NoArgsConstructor
public enum Status {

    PENDING( "PENDING"),
    VERIFIED("VERIFIED"),
    FAILED("FAILED"),
    ALIVE("زنده");
    private String status;

    Status(String status) {
        this.status = status;
    }

}