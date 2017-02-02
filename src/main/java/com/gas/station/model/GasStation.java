package com.gas.station.model;


import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "gas_station")
@Getter
@Setter
public class GasStation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private int id;
    @Column(name = "inne_id")
    private int innerId;
    @Column(nullable = false)
    private Brand brand;
    @ManyToMany
    @JoinTable(
            name = "gs_fuel",
            joinColumns = @JoinColumn(name = "gs_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "fuel_id", referencedColumnName = "id")
    )
    private List<Fuel> fuels;

    @ManyToMany
    @JoinTable(
            name = "gs_service",
            joinColumns = @JoinColumn(name = "GS_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id")
    )
    private List<Service> services;

    @OneToOne
    private Address address;
}
