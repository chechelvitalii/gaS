package com.gas.station.model;

import com.gas.station.model.enums.FuelType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "fuel")
@Getter
public class Fuel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Setter
    @Enumerated
    @Column(nullable = false)
    private FuelType type;
}
