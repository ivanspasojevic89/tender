package com.someco.tenderservice.controler;

import com.someco.tenderservice.api.request.AcceptOfferRequest;
import com.someco.tenderservice.api.request.CreateTenderRequest;
import com.someco.tenderservice.api.response.AcceptOfferResponse;
import com.someco.tenderservice.api.response.CreateTenderResponse;
import com.someco.tenderservice.api.response.GetTenderInfoResponse;
import com.someco.tenderservice.api.response.GetTenderListResponse;
import com.someco.tenderservice.constant.CommonConstants;
import com.someco.tenderservice.model.Offer;
import com.someco.tenderservice.model.Tender;
import com.someco.tenderservice.service.OfferService;
import com.someco.tenderservice.service.TenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "/api/tender")
public class TenderController {

    @Autowired
    private TenderService tenderService;

    @Autowired
    private OfferService offerService;

    @RequestMapping(path = "/createTender", method = RequestMethod.POST)
    public CreateTenderResponse createTender(@RequestBody CreateTenderRequest createTenderRequest) {
        CreateTenderResponse createTenderResponse = new CreateTenderResponse();
        try {
            Tender tender = tenderService.createTender(createTenderRequest);
            createTenderResponse.setTenderID(tender.getTenderID());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            createTenderResponse.setResultCode(CommonConstants.UNEXPECTED_ERROR);
            createTenderResponse.setResponseInfo(ex.getMessage());

        }

        return createTenderResponse;
    }

    @RequestMapping(path = "/getTenderInfo", method = RequestMethod.GET)
    public GetTenderInfoResponse getTenderInfo(@RequestParam Long tenderID) {
        GetTenderInfoResponse getTenderInfoResponse = new GetTenderInfoResponse();
        try {
            Optional<Tender> tender = tenderService.findByID(tenderID);
            if (!tender.isPresent()) {
                getTenderInfoResponse.setResultCode(CommonConstants.TENDER_NOT_FOUND);
                getTenderInfoResponse.setResponseInfo("Tender not found for requested tenderID");
                return getTenderInfoResponse;
            }
            List<Offer> offers = offerService.findByTenderID(tenderID);
            com.someco.tenderservice.dto.Tender tenderDTO = new com.someco.tenderservice.dto.Tender(tender.get());
            offers.forEach(offer -> {
                tenderDTO.addOffer(new com.someco.tenderservice.dto.Offer(offer));
            });
            getTenderInfoResponse.setTender(tenderDTO);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            getTenderInfoResponse.setResultCode(CommonConstants.UNEXPECTED_ERROR);
            getTenderInfoResponse.setResponseInfo(ex.getMessage());
        }

        return getTenderInfoResponse;
    }

    @RequestMapping(path = "/getTenders", method = RequestMethod.GET)
    public GetTenderListResponse getTenders(@RequestParam Long companyID) {
        GetTenderListResponse getTenderListResponse = new GetTenderListResponse();
        List<Tender> tenders = tenderService.findByCompanyID(companyID);
        List<com.someco.tenderservice.dto.Tender> tendersDTO = new LinkedList<>();
        tenders.forEach(tender -> {
            tendersDTO.add(new com.someco.tenderservice.dto.Tender(tender));
        });
        getTenderListResponse.setTenders(tendersDTO);
        return getTenderListResponse;

    }


    @RequestMapping(path = "/acceptOffer", method = RequestMethod.POST)
    public AcceptOfferResponse acceptOffer(@RequestBody AcceptOfferRequest acceptOfferRequest) {
        AcceptOfferResponse acceptOfferResponse = new AcceptOfferResponse();
        try {
            Optional<Tender> tender = tenderService.findByID(acceptOfferRequest.getTenderID());
            if (!tender.isPresent()) {
                acceptOfferResponse.setResultCode(CommonConstants.TENDER_NOT_FOUND);
                acceptOfferResponse.setResponseInfo("Tender not found for requested tenderID");
                return acceptOfferResponse;
            }
            List<Offer> pendingOffers = offerService.findPendingOffers(acceptOfferRequest.getTenderID());
            if (!(pendingOffers != null && pendingOffers.size() > 0)) {
                acceptOfferResponse.setResultCode(CommonConstants.PENDING_OFFER_NOT_FOUND);
                acceptOfferResponse.setResponseInfo("There is no pending offer for specified tender");
                return acceptOfferResponse;
            }

            acceptOfferResponse = tenderService.acceptOffer(tender.get(), pendingOffers, acceptOfferRequest.getOfferID());

        } catch (Exception ex) {
            log.error(ex.getMessage());
            acceptOfferResponse.setResultCode(CommonConstants.UNEXPECTED_ERROR);
            acceptOfferResponse.setResponseInfo(ex.getMessage());
        }

        return acceptOfferResponse;
    }
}
