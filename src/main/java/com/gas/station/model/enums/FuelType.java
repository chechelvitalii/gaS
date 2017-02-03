package com.gas.station.model.enums;

public enum FuelType {
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