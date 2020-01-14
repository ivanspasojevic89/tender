package com.someco.tenderservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Company {
    private Long companyID;
    private String companyName;
    private String companyTaxNumber;
    private String companyRegistrationNumber;

    public Company(com.someco.tenderservice.model.Company companyFromDB) {
        if (companyFromDB != null) {
            companyID = companyFromDB.getCompanyID();
            companyName = companyFromDB.getCompanyName();
            companyTaxNumber = companyFromDB.getCompanyTaxNumber();
            companyRegistrationNumber = companyFromDB.getCompanyRegistrationNumber();
        }
    }
}
