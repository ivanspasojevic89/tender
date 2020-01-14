package com.someco.tenderservice;

import com.someco.tenderservice.api.request.CreateOfferRequest;
import com.someco.tenderservice.api.request.CreateTenderRequest;
import com.someco.tenderservice.constant.CommonConstants;
import com.someco.tenderservice.dao.CompanyDAO;
import com.someco.tenderservice.model.Company;
import com.someco.tenderservice.model.Offer;
import com.someco.tenderservice.model.Tender;
import com.someco.tenderservice.service.OfferService;
import com.someco.tenderservice.service.TenderService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.math.BigDecimal;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TenderServiceApplicationTests {

    @Autowired
    private CompanyDAO companyDAO;

    @Autowired
    private TenderService tenderService;

    @Autowired
    private OfferService offerService;

    @Test
    @Order(1)
    public void populateCompanies() {
        Company company = new Company();
        company.setCompanyName("Company1");
        company.setCompanyRegistrationNumber("123");
        company.setCompanyTaxNumber("1234");
        companyDAO.save(company);

        company = new Company();
        company.setCompanyName("Company2");
        company.setCompanyRegistrationNumber("1234");
        company.setCompanyTaxNumber("123456");
        companyDAO.save(company);

        company = new Company();
        company.setCompanyName("Company3");
        company.setCompanyRegistrationNumber("12723434");
        company.setCompanyTaxNumber("3245646456");
        companyDAO.save(company);
    }

    @Test
    @Order(2)
    public void createTenderAndOffer() throws Exception {
        CreateTenderRequest createTenderRequest = new CreateTenderRequest();
        createTenderRequest.setCompanyID(1L);
        createTenderRequest.setConditions("Company must exists 4 years to be applicable");
        createTenderRequest.setDescription("Tender for building construction");
        Tender tender = tenderService.createTender(createTenderRequest);

        Assert.notNull(tender.getTenderID(), "TenderID not generated");
        Assert.isTrue(CommonConstants.TENDER_STATUS_ISSUED.equals(tender.getTenderStatus()), "Tender not created in appropriate state");

        CreateOfferRequest createOfferRequest = new CreateOfferRequest();
        createOfferRequest.setCompanyID(2L);
        createOfferRequest.setPrice(new BigDecimal(2000));
        createOfferRequest.setTenderID(tender.getTenderID());
        Offer offer = offerService.createOffer(createOfferRequest);

        Assert.notNull(offer.getOfferID(), "OfferID not generated");
        Assert.isTrue(CommonConstants.OFFER_PENDING.equals(offer.getOfferStatus()), "Offer not created in appropriate state");


    }

}