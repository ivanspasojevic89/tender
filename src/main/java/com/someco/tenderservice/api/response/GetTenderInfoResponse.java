package com.someco.tenderservice.api.response;


import com.someco.tenderservice.dto.Tender;
import lombok.Data;

@Data
public class GetTenderInfoResponse extends StandardResponse {
    private Tender tender;
}
