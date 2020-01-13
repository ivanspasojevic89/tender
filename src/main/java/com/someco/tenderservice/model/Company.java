package com.someco.tenderservice.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "company")
@Data
public class Company {
    @Column(name = "CompanyID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyID;

    @Column(name = "CompanyName")
    private String companyName;

    @Column(name = "CompanyTaxNumber")
    private String companyTaxNumber;

    @Column(name = "CompanyRegistrationNumber")
    private String companyRegistrationNumber;

    @Column(name = "CompanyCreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date companyCreated;


}
