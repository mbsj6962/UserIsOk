package com.bsj.kyc.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SabtDto implements Serializable {
    private String name, familyName, NationalCode, pictureAddress, videoAddress, status;
    private Date createdDate, lastModifiedDate;

}
