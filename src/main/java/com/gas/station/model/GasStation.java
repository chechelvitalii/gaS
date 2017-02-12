package com.gas.station.model;


import com.gas.station.model.enums.BrandType;

import java.util.List;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "gas_station")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
    @ManyToMany
    @JoinTable(name = "gas_station_services",
            joinColumns = @JoinColumn(name = "gas_station_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<Service> services;
    @Embedded
    private Address address;
}
