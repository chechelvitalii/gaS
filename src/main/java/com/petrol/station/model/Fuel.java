package com.petrol.station.model;

import com.petrol.station.model.enums.FuelType;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "fuel")
@Getter
@Setter
@EqualsAndHashCode()
@NoArgsConstructor
@RequiredArgsConstructor
public class Fuel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private int id;

    @ManyToMany(mappedBy = "fuels")
    private List<PetrolStation> petrolStations;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FuelType type;

    @NonNull
    @Column(nullable = false)
    //TODO BigDecimal
//    @Digits(message = "Price should have format 999.99", integer = 3, fraction = 2)
    private String price;

}
