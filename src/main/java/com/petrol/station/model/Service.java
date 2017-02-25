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
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ServiceType type;

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", petrolStations=" + petrolStations +
                ", type=" + type +
                '}';
    }
}
