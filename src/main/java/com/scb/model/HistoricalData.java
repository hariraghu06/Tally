package com.scb.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


@Data
public class HistoricalData {
    @JsonProperty("scripname")
    private String scriptName;

    @JsonProperty("scrip_cd")
    private String scriptCode;

    @JsonProperty("StockData")
    private List<PriceData> stockData;
}
