package com.gas.station.model;

import com.gas.station.model.enums.FuelType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.List;

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

    @ManyToMany(mappedBy = "fuels")
    private List<GasStation> gasStations;

    @NonNull
    @Enumerated
    @Column(nullable = false)
    private FuelType type;

    @Column(nullable = false)
    @Digits(message = "Price should have format 999.99", integer = 3, fraction = 2)
    private BigDecimal price;

}
