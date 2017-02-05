package com.gas.station.service.feed.impl;

import com.gas.station.model.Brand;
import com.gas.station.model.enums.BrandType;
import com.gas.station.repository.BrandRepository;
import com.gas.station.service.feed.FeedingService;
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
            Brand newBrand = new Brand();
            newBrand.setType(brandType);
            brandsForSave.add(newBrand);
        }
        repository.save(brandsForSave);
        return brandsForSave.size();
    }
}
