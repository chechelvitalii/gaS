package com.gas.station.model;

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
}