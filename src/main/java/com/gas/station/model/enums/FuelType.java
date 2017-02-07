package com.gas.station.model.enums;

import com.gas.station.exception.ConvertTypeException;
import lombok.Getter;

import java.util.Optional;

@Getter
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

    public static FuelType getTypeByName(String name) {
        Optional<FuelType> typeByName = Optional.empty();
        for (int i = 0; i < values().length; i++) {
            if (values()[i].getName().equals(name)) {
                typeByName = Optional.of(values()[i]);
                break;
            }
        }
        return typeByName
                .orElseThrow(() -> new ConvertTypeException(name));
    }
}