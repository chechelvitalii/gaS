package com.gas.station.model;

import com.gas.station.model.enums.FuelType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "fuel")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Fuel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private int id;

    @NonNull
    @Enumerated
    @Column(nullable = false)
    private FuelType type;
}
