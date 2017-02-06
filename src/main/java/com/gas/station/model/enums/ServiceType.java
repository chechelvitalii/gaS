package com.gas.station.model.enums;

import lombok.Getter;

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
        ServiceType typeByName = null;
        for (int i = 0; i < values().length; i++) {
            if (values()[i].getName().equals(name)) {
                typeByName= values()[i];
                break;
            }
        }
        return typeByName;
    }
}