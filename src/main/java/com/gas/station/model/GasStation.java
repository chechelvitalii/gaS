package com.gas.station.model;


import java.util.List;

import javax.persistence.*;

import com.gas.station.model.enums.BrandType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "gas_station")
@Getter
@Setter
@Builder
public class GasStation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private int id;
    @Column(name = "inner_id")
    private int innerId;
    @Column(nullable = false)
    private BrandType brandType;
    @OneToMany
    private List<Fuel> fuels;
    @OneToMany
    private List<Service> services;
    @OneToOne
    private Address address;
}
