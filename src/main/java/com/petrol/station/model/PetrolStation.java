package com.petrol.station.model;


import com.petrol.station.model.enums.BrandType;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "petrol_station")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetrolStation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private int id;
    @Column(name = "inner_id")
    private int innerId;
    @Column(nullable = false)
    private BrandType brandType;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "petrol_station_fuels",
            joinColumns = @JoinColumn(name = "petrol_station_id"),
            inverseJoinColumns = @JoinColumn(name = "fuel_id"))
    private List<Fuel> fuels;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "petrol_station_services",
            joinColumns = @JoinColumn(name = "petrol_station_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private List<Service> services;
    @Embedded
    private Address address;

    @Override
    public String toString() {
        return "\nPetrolStation:" +
                "\n id=" + id +
                "\n innerId=" + innerId +
                "\n brandType=" + brandType +
                "\n fuels=" + fuels +
                "\n services=" + services +
                "\n address=" + address;
    }
}
