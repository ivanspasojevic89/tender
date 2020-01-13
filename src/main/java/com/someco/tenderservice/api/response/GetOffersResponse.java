package com.someco.tenderservice.api.response;

import com.someco.tenderservice.model.Offer;
import lombok.Data;

import java.util.List;

@Data
public class GetOffersResponse extends StandardResponse {
    private List<Offer> offers;
}
