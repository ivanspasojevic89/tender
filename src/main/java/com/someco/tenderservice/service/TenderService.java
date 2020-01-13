package com.someco.tenderservice.service;

import com.someco.tenderservice.api.request.CreateTenderRequest;
import com.someco.tenderservice.api.response.AcceptOfferResponse;
import com.someco.tenderservice.model.Offer;
import com.someco.tenderservice.model.Tender;

import java.util.List;
import java.util.Optional;

public interface TenderService {
    Tender createTender(CreateTenderRequest createTenderRequest) throws Exception;

    Optional<Tender> findByID(Long tenderID);

    List<Tender> findByCompanyID(Long companyID);

    AcceptOfferResponse acceptOffer(Tender tender, List<Offer> pendingOffers, Long offerID) throws Exception;
}
