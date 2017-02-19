package com.petrol.station.service.feed.impl;

import com.petrol.station.model.Brand;
import com.petrol.station.model.enums.BrandType;
import com.petrol.station.repository.BrandRepository;
import com.petrol.station.service.feed.FeedingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BrandFeedingService extends FeedingService<BrandType> {

    @Autowired
    private BrandRepository repository;

    public BrandFeedingService() {
        super(BrandType.values());
    }

    @Override
    public int feed() {
        List<Brand> brandsForSave = new ArrayList<>();
        for (BrandType brandType : dataFeed) {
            brandsForSave.add(new Brand(brandType));
        }
        repository.save(brandsForSave);
        return brandsForSave.size();
    }
}
