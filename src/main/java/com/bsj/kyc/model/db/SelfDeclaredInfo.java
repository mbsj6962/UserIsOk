package com.bsj.kyc.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "self_declared_information")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelfDeclaredInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 65)
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 65)
    @Column(name = "family_name", nullable = false)
    private String familyName;

    @Column(name = "nationalCode", nullable = false)
    private String nationalCode;

    @CreatedDate
    @Column(name = "created_date")
    private Date createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    @Column(name = "picture_address")
    private String pictureAddress;

    @Column(name = "video_address")
    private String videoAddress;

    @Column(name = "status")
    private String status;


}
