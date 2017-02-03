package com.gas.station.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
    @ManyToMany(mappedBy = "gasStations")
    private List<Fuel> fuels;
    @ManyToMany(mappedBy = "gasStations")
    private List<Service> services;
    @OneToOne
    private Address address;
}
