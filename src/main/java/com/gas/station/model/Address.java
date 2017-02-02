package com.gas.station.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "address")
@Getter
@Setter
public class Address {
    @Id
    private int id;
//@Column
//private GasStation gasStation;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private double lat;
    @Column(nullable = false)
    private double lng;
}
