package com.gas.station.facade;

import com.gas.station.model.enums.BrandType;
import com.gas.station.repository.GasStationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StationService {
    @Autowired
    private GasStationRepository gasStationRepository;

    //TODO count updated station ?
    public void updateGasStationByType(BrandType brand) {
    }
}
