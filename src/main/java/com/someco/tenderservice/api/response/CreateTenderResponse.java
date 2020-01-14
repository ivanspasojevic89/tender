package com.someco.tenderservice.api.response;

import lombok.Data;

@Data
public class CreateTenderResponse extends StandardResponse {
    private Long tenderID;
}
