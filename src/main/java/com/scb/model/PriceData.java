package com.scb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PriceData {
    @JsonProperty("Dates")
    private String date;

    @JsonProperty("qe_close")
    private String closePrice;


}
