package com.gas.station.service.feed;

import com.gas.station.model.enums.BrandType;
import com.gas.station.model.enums.FuelType;
import org.springframework.data.repository.CrudRepository;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public abstract class FeedingService<T extends Enum>{
    protected List<T> dataFeed;

    public FeedingService(T[] dataFeed) {
        this.dataFeed = Arrays.asList(dataFeed);
    }

    public abstract int feed();
}
