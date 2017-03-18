package com.petrol.station.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetrolStationDto {
    private int id;
    private String brand;
    private String city;
    private String street;
    private String lat;
    private String lng;

}
