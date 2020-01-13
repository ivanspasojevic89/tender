package com.someco.tenderservice.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetOffersRequest {
    @JsonProperty(required = true)
    private Long companyID;
    private Long tenderID;
}
