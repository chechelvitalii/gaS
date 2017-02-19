package com.petrol.station.model.enums;

import com.petrol.station.exception.ResolveTypeException;
import lombok.Getter;

import java.util.Optional;

@Getter
public enum ServiceType {
    WOG_CAFE("WOG cafe"),
    WOG_BURGER("WOG-бургер"),
    WI_FI("Wi-Fi"),
    SHOP("Магазин"),
    PHONE("Телефон"),
    WASHING("Мойка"),
    AUTO_WASHING("Автомойка"),
    PARKING("Парковка"),
    PARKING_2("Паркинг"),
    TIR_PARKING("Паркинг большого транспорта"),
    TIRE_PUMPING("Подкачка шин"),
    CHARGER("Электро-заправка обычная"),
    SUPERCHARGER("Электро-заправка скоростная (Supercharger)"),
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
                .orElseThrow(() -> new ResolveTypeException(name));
    }
}