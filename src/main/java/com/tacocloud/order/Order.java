package com.tacocloud.order;

import com.tacocloud.taco.Taco;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class Order {

    private Long id;
    private Instant placedAt;
    private List<Taco> tacos;
    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZipCode;
    private String creditCardNumber;
    private String creditCardExpiration;
    private String creditCardVerificationValue;

}
