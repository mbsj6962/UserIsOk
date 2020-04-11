package com.bsj.kyc.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "sabt_fetched_information")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SabtInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 65)
    @Column(name = "name")
    private String name;

    @Size(max = 65)
    @Column(name = "family_name")
    private String familyName;

    @Column(name = "birth_date")
    private Date birthdate;

    @Column(name = "identity_number")
    private String identityNumber;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "living_status")
    private String livingStatus;

    @Column(name = "identity_serie")
    private String identitySerie;

    @Column(name = "identity_status")
    private String identityStatus;

    @Column(name = "postal_code")
    private String postalCode;

    @Size(max = 2147483646)
    @Column(name = "picture")
    private String picture;

}
