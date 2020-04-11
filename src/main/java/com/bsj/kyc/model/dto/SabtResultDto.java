package com.bsj.kyc.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SabtResultDto {

    private String name, familyName, birthdate, identityNumber, fatherName, livingStatus, identitySerie, identityStatus,
    postalCode, picture;


    public Date convertDate(String birthdate) throws ParseException {
        return new SimpleDateFormat("yyyy/MM/dd").parse(birthdate);
    }

}
