package com.gas.station.model;

import com.gas.station.model.enums.BrandType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "brand")
@Getter
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Setter
    @Enumerated
    @Column(nullable = false)
    private BrandType type;
}
