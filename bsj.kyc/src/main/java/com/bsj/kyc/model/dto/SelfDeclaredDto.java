package com.bsj.kyc.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelfDeclaredDto {
    private String name, familyName, NationalCode;

}
