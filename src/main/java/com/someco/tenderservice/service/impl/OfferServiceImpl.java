package com.someco.tenderservice.service.impl;

import com.someco.tenderservice.api.request.CreateOfferRequest;
import com.someco.tenderservice.api.request.GetOffersRequest;
import com.someco.tenderservice.constant.CommonConstants;
import com.someco.tenderservice.dao.CompanyDAO;
import com.someco.tenderservice.dao.OfferDAO;
import com.someco.tenderservice.dao.TenderDAO;
import com.someco.tenderservice.model.Company;
import com.someco.tenderservice.model.Offer;
import com.someco.tenderservice.model.Tender;
import com.someco.tenderservice.service.OfferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OfferServiceImpl implements OfferService {

    @Autowired
    private OfferDAO offerDAO;

    @Autowired
    private TenderDAO tenderDAO;

    @Autowired
    private CompanyDAO companyDAO;

    @Override
    public Offer createOffer(CreateOfferRequest createOfferRequest) throws Exception {

        Optional<Company> company = companyDAO.findById(createOfferRequest.getCompanyID());
        if (!company.isPresent()) {
            throw new Exception("Invalid companyID");
        }

        Optional<Tender> tender = tenderDAO.findById(createOfferRequest.getTenderID());
        if (!tender.isPresent()) {
            throw new Exception("Invalid tenderID");
        }

        if (CommonConstants.TENDER_STATUS_COMPLETED.equals(tender.get().getTenderStatus())) {
            throw new Exception("Cannot create an offer for completed tenders");
        }

        Offer offer = Offer.builder()
                .offerPrice(createOfferRequest.getPrice())
                .offerStatus(CommonConstants.OFFER_PENDING)
                .company(company.get())
                .tender(tender.get())
                .build();
        offerDAO.save(offer);

        return offer;
    }

    @Override
    public List<Offer> findByTenderID(Long tenderID) {
        return offerDAO.finOffersByTenderID(tenderID);
    }

    @Override
    public List<Offer> findPendingOffers(Long tenderID) {
        return offerDAO.findByTenderIDAndStatus(tenderID, CommonConstants.OFFER_PENDING);
    }

    @Override
    public List<Offer> findBy(GetOffersRequest getOffersRequest) {
        return offerDAO.findOfferByCriteria(getOffersRequest);
    }
}
