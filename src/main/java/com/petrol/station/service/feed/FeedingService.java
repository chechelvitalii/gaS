package com.petrol.station.service.feed;

import java.util.Arrays;
import java.util.List;

public abstract class FeedingService<T extends Enum>{
    protected List<T> dataFeed;

    public FeedingService(T[] dataFeed) {
        this.dataFeed = Arrays.asList(dataFeed);
    }

    public abstract int feed();
}
