package com.gas.station.model;

import com.gas.station.model.enums.ServiceType;
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
    private List<GasStation> stations;

    @NonNull
    @Enumerated
    @Column(nullable = false)
    private ServiceType type;

}
