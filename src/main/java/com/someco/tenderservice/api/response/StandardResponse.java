package com.someco.tenderservice.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandardResponse {
    protected int resultCode = 0;
    protected String responseInfo = "OK";
}
