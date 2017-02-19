package com.petrol.station.model;

import com.petrol.station.model.enums.ServiceType;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "service")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private int id;

    @ManyToMany(mappedBy = "services")
    private List<PetrolStation> petrolStations;

    @NonNull
    @Enumerated
    @Column(nullable = false)
    private ServiceType type;

}
