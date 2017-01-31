package com.gas.station.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gas_station")
@Getter
@Setter
public class GasStation {
    @Id
    private int id;
    @Id
    private Brand brand;
    @Column
    private Fuel fuel;
    @Column
    private Service service;
    @Column
    private Address address;


    enum Brand{
        WOG
    }
}
