package com.bsj.kyc.utills;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum Address {

    SABT_PICTURE("/src/main/resources/Storage/SabtPicture/"),
    CUSTOMER_PICTURE("/src/main/resources/Storage//CustomerPicture/"),
    CUSTOMER_VIDEO("/src/main/resources/Storage/CustomerVideo/");

    private String address;

    Address(String address){ this.address = address; }
}