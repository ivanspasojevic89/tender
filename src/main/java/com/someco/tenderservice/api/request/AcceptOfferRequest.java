package com.someco.tenderservice.api.request;

import lombok.Data;

@Data
public class AcceptOfferRequest {
    private Long offerID;
    private Long tenderID;
}
