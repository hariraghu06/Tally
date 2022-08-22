package com.scb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scb.RestTemplateConfiguration;
import com.scb.model.HistoricalData;
import com.scb.model.PriceData;
import com.scb.model.PriceEntity;
import com.scb.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bse")
public class BSEDataController {

    @Autowired
    RestTemplateConfiguration restTemplateConfiguration;

    @Autowired
    PriceRepository priceRepository;

    @PutMapping("/historicalData")
    public String getHistoricalStockPrice() throws JsonProcessingException {
        String historicalPriceURl = "https://api.bseindia.com/BseIndiaAPI/api/StockpricesearchData/w?";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("pageType", "0");
        params.add("rbType", "D");
        params.add("Scode", "505509");
        params.add("MonthDate", "06/08/2022");
        params.add("YearDate", "10/08/2022");

        String url = UriComponentsBuilder.fromUriString(historicalPriceURl)
                .queryParams(params).toUriString();

        String historicalStockPrice = restTemplateConfiguration.restTemplate()
                .getForEntity(url, String.class, params).getBody();

        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        HistoricalData data = objectMapper.readValue(historicalStockPrice, HistoricalData.class);


        List<PriceData> dataList = new ArrayList<>();
        dataList=data.getStockData();
        dataList.stream().forEach(
                a->{PriceEntity priceEntity = new PriceEntity();
                    priceEntity.setCode(data.getScriptCode());
                    priceEntity.setScriptName(data.getScriptName());
                    priceEntity.setDate(a.getDate());
                    priceEntity.setPrice(a.getClosePrice());
                   priceRepository.save(priceEntity);
                    System.out.println("save successfull");
                }
        );




        System.out.println(data.toString());
        return historicalStockPrice;
    }
}