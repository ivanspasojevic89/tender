package com.someco.tenderservice.api.response;

import com.someco.tenderservice.model.Tender;
import lombok.Data;

import java.util.List;

@Data
public class GetTenderListResponse extends StandardResponse {
    private List<Tender> tenders;
}
