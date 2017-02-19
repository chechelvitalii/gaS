package com.petrol.station.service.feed.impl;

import com.petrol.station.model.Fuel;
import com.petrol.station.model.enums.FuelType;
import com.petrol.station.repository.FuelRepository;
import com.petrol.station.service.feed.FeedingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FuelFeedingService extends FeedingService<FuelType> {

    @Autowired
    private FuelRepository repository;

    public FuelFeedingService() {
        super(FuelType.values());
    }

    @Override
    public int feed() {
        List<Fuel> fuelsForSave = new ArrayList<>();
        for (FuelType fuelType : dataFeed) {
            Fuel newFuel = new Fuel();
            newFuel.setType(fuelType);
            fuelsForSave.add(newFuel);
        }
        repository.save(fuelsForSave);
        return fuelsForSave.size();
    }
}
