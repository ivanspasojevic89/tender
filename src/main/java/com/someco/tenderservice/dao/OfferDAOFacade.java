package com.someco.tenderservice.dao;

import com.someco.tenderservice.api.request.GetOffersRequest;
import com.someco.tenderservice.model.Offer;

import java.util.List;

public interface OfferDAOFacade {

    List<Offer> findOfferByCriteria(GetOffersRequest getOffersRequest);
}
