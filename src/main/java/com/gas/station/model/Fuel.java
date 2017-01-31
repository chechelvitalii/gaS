package com.gas.station.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fuel")
@Getter
@Setter
public class Fuel {
    @Id
    private int id;
    @Column
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
