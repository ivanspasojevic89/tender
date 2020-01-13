package com.someco.tenderservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Offer {
    private Long offerID;
    private BigDecimal offerPrice;
    private String offerStatus;
    private Date offerCreated;
    private Company company;
    private Tender tender;

    public Offer(com.someco.tenderservice.model.Offer offerFromDB) {
        if (offerFromDB != null) {
            offerID = offerFromDB.getOfferID();
            offerPrice = offerFromDB.getOfferPrice();
            offerStatus = offerFromDB.getOfferStatus();
            offerCreated = offerFromDB.getOfferCreated();
            company = new Company(offerFromDB.getCompany());
        }
    }

}
