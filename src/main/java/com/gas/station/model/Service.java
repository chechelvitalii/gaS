package com.gas.station.model;

import com.gas.station.model.enums.ServiceType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "service")
@Getter
@EqualsAndHashCode
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Setter
    @Enumerated
    @Column(nullable = false)
    private ServiceType type;
}
