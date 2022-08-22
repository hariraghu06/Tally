package com.scb.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "HISTORICAL_DATA")
public class PriceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name ="SCRIPT_NAME")
    private String scriptName;

    private String code;
    private String date;
    private String price;


}
