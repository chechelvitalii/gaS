package com.petrol.station.model.enums;

import com.petrol.station.exception.ResolveTypeException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@AllArgsConstructor
@Getter
public enum FuelType {
    A_95("A-95", "95euros-big.png"),//95euros-big.png
    MUSTANG_92("92 MUSTANG", "92mustang-big.png"),//92mustang-big.png
    MUSTANG_95("95 MUSTANG", "95mustang-big.png"),//95mustang-big.png
    MUSTANG_100("100 MUSTANG", "100mustang-big.png"),//100mustang-big.png
    MUSTANG_DT("ДП MUSTANG", "pdMustang-big.png"),//pdMustang-big.png
    MUSTANG_LPG("LPG MUSTANG", "lpgMustang-big.png"),//lpgMustang-big.png
    MUSTANG_DT_PLUS("ДП MUSTANG+", "dp+Mustang-big.png");//dp+Mustang-big.png

    private String name;
    private String detectValue;

    public static FuelType getFuelTypeByName(String name) {
        Optional<FuelType> fuelType = Optional.empty();
        for (int i = 0; i < values().length; i++) {
            if (values()[i].getName().equals(name)) {
                fuelType = Optional.of(values()[i]);
                break;
            }
        }
        return fuelType
                .orElseThrow(() -> new ResolveTypeException(name));
    }

    public static FuelType getFuelTypeByDetectValue(String detectValue) {
        Optional<FuelType> fuelType = Optional.empty();
        for (int i = 0; i < values().length; i++) {
            if (detectValue.contains(values()[i].getDetectValue())) {
                fuelType = Optional.of(values()[i]);
                break;
            }
        }
        return fuelType
                .orElseThrow(() -> new ResolveTypeException(detectValue));
    }
}