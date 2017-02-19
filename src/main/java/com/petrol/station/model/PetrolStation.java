package com.petrol.station.model;


import com.petrol.station.model.enums.BrandType;

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
@Table(name = "petrol_station")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PetrolStation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private int id;
    @Column(name = "inner_id")
    private int innerId;
    @Column(nullable = false)
    private BrandType brandType;
    @ManyToMany
    @JoinTable(name = "petrol_station_fuels",
            joinColumns = @JoinColumn(name = "petrol_station_id"),
            inverseJoinColumns = @JoinColumn(name = "fuel_id"))
    private List<Fuel> fuels;
    @ManyToMany
    @JoinTable(name = "petrol_station_services",
            joinColumns = @JoinColumn(name = "petrol_station_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<Service> services;
    @Embedded
    private Address address;
}
