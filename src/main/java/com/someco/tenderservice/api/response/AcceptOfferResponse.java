package com.someco.tenderservice.api.response;

import com.someco.tenderservice.dto.Offer;
import com.someco.tenderservice.dto.Tender;
import lombok.Data;

import java.util.List;

@Data
public class AcceptOfferResponse extends StandardResponse {
    private Tender tender;
    private List<Offer> offers;
}
