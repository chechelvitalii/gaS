package com.gas.station.facade;

import com.gas.station.service.feed.FeedingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class FeedingFacade {
    @Autowired
    private List<FeedingService> feedingServices;

    public void performStaticFeeding() {
        log.info("Feeding . . . . .");
        for (FeedingService feedingService : feedingServices) {
            int fedCount = feedingService.feed();
            String feedServiceName = feedingService.getClass().getSimpleName();
            log.info("Fed by {} ---> count : {} ", feedServiceName, fedCount);
        }
        log.info("Feeding finished");
    }
}
