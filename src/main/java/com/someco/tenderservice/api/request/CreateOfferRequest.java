package com.someco.tenderservice.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOfferRequest {
    private Long tenderID;
    private BigDecimal price;
    private Long companyID;
}
