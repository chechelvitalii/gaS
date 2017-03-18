package com.petrol.station.converter;

import com.petrol.station.dto.PetrolStationDto;
import com.petrol.station.model.PetrolStation;
import org.springframework.stereotype.Component;

@Component
public class PetrolStationConverter extends AbstractConverter<PetrolStation,PetrolStationDto> {
    @Override
    public PetrolStationDto convert(PetrolStation petrolStation) {
        PetrolStationDto petrolStationDto = new PetrolStationDto();
        petrolStationDto.setCity(petrolStation.getAddress().getCity());
        petrolStationDto.setStreet(petrolStation.getAddress().getStreet());
        petrolStationDto.setLat(petrolStation.getAddress().getLat());
        petrolStationDto.setLng(petrolStation.getAddress().getLng());
        petrolStationDto.setBrand(petrolStation.getBrandType().name());
        petrolStationDto.setId(petrolStation.getId());
        return petrolStationDto;
    }
}
