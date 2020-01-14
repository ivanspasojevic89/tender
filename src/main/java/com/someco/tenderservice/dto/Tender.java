package com.someco.tenderservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tender {
    private Long tenderID;
    private String tenderDescription;
    private String tenderConditions;
    private String tenderStatus;
    private Date tenderCreated;
    private Date tenderCompleted;
    private Company company;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Offer> offers;


    public Tender(com.someco.tenderservice.model.Tender tenderFromDB) {
        if (tenderFromDB != null) {
            tenderID = tenderFromDB.getTenderID();
            tenderDescription = tenderFromDB.getTenderDescription();
            tenderConditions = tenderFromDB.getTenderConditions();
            tenderStatus = tenderFromDB.getTenderStatus();
            tenderCreated = tenderFromDB.getTenderCreated();
            tenderCompleted = tenderFromDB.getTenderCompleted();
            company = new Company(tenderFromDB.getCompany());
            offers = new LinkedList<>();
        }
    }

    public void addOffer(Offer offer) {
        offers.add(offer);
    }
}
