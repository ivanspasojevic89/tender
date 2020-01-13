package com.someco.tenderservice.api.response;


import com.someco.tenderservice.model.Offer;
import com.someco.tenderservice.model.Tender;
import lombok.Data;

import java.util.List;

@Data
public class GetTenderInfoResponse extends StandardResponse {
    private Tender tender;
    private List<Offer> offers;
}
