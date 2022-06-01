package com.tacocloud.order;

import com.tacocloud.taco.Taco;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "placed_at")
    private Instant placedAt;

    @ManyToMany
    @JoinTable(
            name = "orders_tacos",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "taco_id")
    )
    private List<Taco> tacos = new ArrayList<>();

    @Column(name = "delivery_name")
    private String deliveryName;

    @Column(name = "delivery_street")
    private String deliveryStreet;

    @Column(name = "delivery_city")
    private String deliveryCity;

    @Column(name = "delivery_state")
    private String deliveryState;

    @Column(name = "delivery_zip")
    private String deliveryZipCode;

    @Column(name = "cc_number")
    private String creditCardNumber;

    @Column(name = "cc_expiry")
    private String creditCardExpiration;

    @Column(name = "cc_verify")
    private String creditCardVerificationValue;

}
