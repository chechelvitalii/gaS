package com.gas.station.model;

import com.gas.station.model.enums.BrandType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "brand")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private int id;

    @NonNull
    @Enumerated
    @Column(nullable = false)
    private BrandType type;
}
