package com.gas.station.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "service")
@Getter
@Setter
public class Service {

    @Id
    private int id;
    @Column
    private ServiceType type;

    enum ServiceType {
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
}
