package com.someco.tenderservice.service.impl;

import com.someco.tenderservice.api.request.CreateTenderRequest;
import com.someco.tenderservice.api.response.AcceptOfferResponse;
import com.someco.tenderservice.constant.CommonConstants;
import com.someco.tenderservice.dao.CompanyDAO;
import com.someco.tenderservice.dao.OfferDAO;
import com.someco.tenderservice.dao.TenderDAO;
import com.someco.tenderservice.model.Company;
import com.someco.tenderservice.model.Offer;
import com.someco.tenderservice.model.Tender;
import com.someco.tenderservice.service.TenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class TenderServiceImpl implements TenderService {

    @Autowired
    private TenderDAO tenderDAO;

    @Autowired
    private CompanyDAO companyDAO;

    @Autowired
    private OfferDAO offerDAO;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Tender createTender(CreateTenderRequest createTenderRequest) throws Exception {

        Optional<Company> company = companyDAO.findById(createTenderRequest.getCompanyID());
        if (!company.isPresent()) {
            throw new Exception("Invalid companyID");
        }
        Tender tender = Tender.builder()
                .tenderDescription(createTenderRequest.getDescription())
                .tenderConditions(createTenderRequest.getConditions())
                .company(company.get())
                .tenderStatus(CommonConstants.TENDER_STATUS_ISSUED)
                .build();

        tenderDAO.save(tender);
        return tender;
    }

    @Override
    public Optional<Tender> findByID(Long tenderID) {
        return tenderDAO.findById(tenderID);
    }

    @Override
    public List<Tender> findByCompanyID(Long companyID) {
        return tenderDAO.findByCompanyID(companyID);
    }

    @Override
    @Transactional
    public AcceptOfferResponse acceptOffer(Tender tender, List<Offer> pendingOffers, Long offerID) throws Exception {
        Optional<Offer> offerForAccepting = pendingOffers.stream().filter(offer ->
                offer.getOfferID().equals(offerID)).findFirst();
        if (!offerForAccepting.isPresent()) {
            throw new Exception("Invalid offerID");
        }
        Offer offer = offerForAccepting.get();
        offer.setOfferStatus(CommonConstants.OFFER_ACCEPTED);
        offerDAO.save(offer);

        offerDAO.setPendingOffersAsRejected(tender.getTenderID());

        tender.setTenderStatus(CommonConstants.TENDER_STATUS_COMPLETED);
        tenderDAO.save(tender);

        AcceptOfferResponse acceptOfferResponse = new AcceptOfferResponse();
        com.someco.tenderservice.dto.Tender tenderDTO = new com.someco.tenderservice.dto.Tender(tender);
        tenderDTO.addOffer(new com.someco.tenderservice.dto.Offer(offer));
        acceptOfferResponse.setTender(tenderDTO);

        return acceptOfferResponse;
    }

}
