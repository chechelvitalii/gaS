package com.petrol.station.service.feed.impl;

import com.petrol.station.model.Service;
import com.petrol.station.model.enums.ServiceType;
import com.petrol.station.repository.ServiceRepository;
import com.petrol.station.service.feed.FeedingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceFeedingService extends FeedingService<ServiceType> {

    @Autowired
    private ServiceRepository repository;

    public ServiceFeedingService() {
        super(ServiceType.values());
    }

    @Override
    public int feed() {
        List<Service> servicesForSave = new ArrayList<>();
        for (ServiceType serviceType : dataFeed) {
            servicesForSave.add(new Service(serviceType));
        }
        repository.save(servicesForSave);
        return servicesForSave.size();
    }
}