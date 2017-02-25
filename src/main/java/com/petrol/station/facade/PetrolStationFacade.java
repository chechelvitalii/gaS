package com.petrol.station.facade;

import com.petrol.station.model.PetrolStation;
import com.petrol.station.model.enums.BrandType;
import com.petrol.station.repository.PetrolStationRepository;
import com.petrol.station.service.parser.WogParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PetrolStationFacade {
    @Autowired
    private PetrolStationRepository petrolStationRepository;
    @Autowired
    private WogParser wogParser;

    public List<PetrolStation> updateAllPetrolStations() {
        List<PetrolStation> petrolStations = wogParser.parsePetrolStations();
        return petrolStationRepository.save(petrolStations);
    }

    public List<PetrolStation> getAllPetrolStations(){
        return petrolStationRepository.findAll();
    }
}
