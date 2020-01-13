package com.someco.tenderservice.controler;


import com.someco.tenderservice.api.request.CreateOfferRequest;
import com.someco.tenderservice.api.request.GetOffersRequest;
import com.someco.tenderservice.api.response.CreateOfferResponse;
import com.someco.tenderservice.api.response.GetOffersResponse;
import com.someco.tenderservice.constant.CommonConstants;
import com.someco.tenderservice.dto.Tender;
import com.someco.tenderservice.model.Offer;
import com.someco.tenderservice.service.OfferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/api/offer")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @RequestMapping(path = "/createOffer", method = RequestMethod.POST)
    public CreateOfferResponse createOffer(@RequestBody CreateOfferRequest createOfferRequest) {
        CreateOfferResponse createOfferResponse = new CreateOfferResponse();
        try {
            Offer offer = offerService.createOffer(createOfferRequest);
            createOfferResponse.setOfferID(offer.getOfferID());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            createOfferResponse.setResultCode(CommonConstants.UNEXPECTED_ERROR);
            createOfferResponse.setResponseInfo(ex.getMessage());

        }

        return createOfferResponse;
    }

    @RequestMapping(path = "/getOffers", method = RequestMethod.POST)
    public GetOffersResponse getOffers(@RequestBody GetOffersRequest getOffersRequest) {
        GetOffersResponse getOffersResponse = new GetOffersResponse();
        try {
            if (getOffersRequest.getCompanyID() == null) {
                getOffersResponse.setResultCode(CommonConstants.INVALID_BODY);
                getOffersResponse.setResponseInfo("CompanyID should be provided");
                return getOffersResponse;
            }
            List<Offer> offers = offerService.findBy(getOffersRequest);
            List<com.someco.tenderservice.dto.Offer> offersDTO = new LinkedList<>();
            offers.forEach(offer -> {
                com.someco.tenderservice.dto.Offer offerDTO = new com.someco.tenderservice.dto.Offer(offer);
                offerDTO.setTender(new Tender(offer.getTender()));
                offersDTO.add(offerDTO);
            });
            getOffersResponse.setOffers(offersDTO);

        } catch (Exception ex) {
            log.error(ex.getMessage());
            getOffersResponse.setResultCode(CommonConstants.UNEXPECTED_ERROR);
            getOffersResponse.setResponseInfo(ex.getMessage());

        }

        return getOffersResponse;
    }
}
