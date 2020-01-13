package com.someco.tenderservice.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTenderRequest {
    private String description;
    private String conditions;
    private Long companyID;
}
