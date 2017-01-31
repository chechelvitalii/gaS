package com.gas.station.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address")
@Getter
@Setter
public class Address {
    @Id
    private int id;
    @Column
    private String city;
    @Column
    private String street;
    @Column
    private double lat;
    @Column
    private double lng;
}
