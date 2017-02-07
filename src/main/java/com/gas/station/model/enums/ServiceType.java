package com.gas.station.model.enums;

import com.gas.station.exception.ConvertTypeException;
import lombok.Getter;

import java.util.Optional;

@Getter
public enum ServiceType {
    CAFE("WOG cafe"),
    SHOP("Магазин"),
    WASHING("Мойка"),
    PHONE("Телефон"),
    PARKING("Парковка"),
    COUPONS("Талоны"),
    CREDIT_CARDS("Кредитные карты"),
    FUEL_CARDS("Топливные карты"),
    INSURANCE("Страхование");

    private String name;

    ServiceType(String name) {
        this.name = name;
    }

    public static ServiceType getTypeByName(String name) {
        Optional<ServiceType> typeByName = Optional.empty();
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