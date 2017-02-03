package com.gas.station.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "fuel")
@Getter
@Setter
public class Fuel {
    @Id
    private int id;
    @ManyToMany
    @JoinTable(
            name = "fuel-_gs",
            joinColumns =  @JoinColumn(name = "fuel_id", referencedColumnName = "id"),
            inverseJoinColumns =@JoinColumn(name = "gs_id", referencedColumnName = "id")
    )
    private List<GasStation> gasStations;
    @Column(nullable = false)
    private FuelType type;


    enum FuelType {
        A_95("A-95"),
        MUSTANG_92("92 MUSTANG"),
        MUSTANG_95("95 MUSTANG"),
        MUSTANG_100("100 MUSTANG"),
        MUSTANG_DT("ДП MUSTANG"),
        MUSTANG_LPG("LPG MUSTANG"),
        MUSTANG_DT_PLUS("ДП MUSTANG+");

        private String name;

        FuelType(String name) {
            this.name = name;
        }
    }
}
