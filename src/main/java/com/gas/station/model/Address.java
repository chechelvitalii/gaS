package com.gas.station.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "address")
@Getter
@Setter
@EqualsAndHashCode
public class Address {
//    @Id
//    private int id;
    //TODO add reference
//@Column
//private GasStation gasStation;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false) //TODO double ?
    private String lat;
    @Column(nullable = false) //TODO double ?
    private String lng;
}
