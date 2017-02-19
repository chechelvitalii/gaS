package com.petrol.station.facade;

import com.petrol.station.model.enums.BrandType;
import com.petrol.station.repository.PetrolStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StationService {
    @Autowired
    private PetrolStationRepository petrolStationRepository;

    //TODO count updated station ?
    public void updatePetrolStationByType(BrandType brand) {
    }
}
