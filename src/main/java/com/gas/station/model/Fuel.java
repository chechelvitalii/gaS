package com.gas.station.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.gas.station.model.enums.FuelType;
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
            joinColumns = @JoinColumn(name = "fuel_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "gs_id", referencedColumnName = "id")
    )
    private List<GasStation> gasStations;
    @Column(nullable = false)
    private FuelType type;
}
