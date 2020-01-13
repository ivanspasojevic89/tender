package com.someco.tenderservice.service;

import com.someco.tenderservice.api.request.CreateOfferRequest;
import com.someco.tenderservice.api.request.GetOffersRequest;
import com.someco.tenderservice.model.Offer;

import java.util.List;

public interface OfferService {
    Offer createOffer(CreateOfferRequest createOfferRequest) throws Exception;

    List<Offer> findByTenderID(Long tenderID);

    List<Offer> findPendingOffers(Long tenderID);

    List<Offer> findBy(GetOffersRequest getOffersRequest);
}
