package com.petrol.station.controller;


import com.petrol.station.facade.PetrolStationFacade;
import com.petrol.station.model.PetrolStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("station")
public class PetrolStationController {

    @Autowired
    private PetrolStationFacade petrolStationFacade;

    @RequestMapping("all")
    public List<PetrolStation> getAllPetrolStations() {
        return petrolStationFacade.getAllPetrolStations();
    }

    @RequestMapping(value = "update/all", method = RequestMethod.POST)
    public List<PetrolStation> updateAllPetrolStations() {
        return petrolStationFacade.updateAllPetrolStations();
    }
}
