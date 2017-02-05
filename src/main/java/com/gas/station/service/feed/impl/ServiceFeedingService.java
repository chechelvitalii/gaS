package com.gas.station.service.feed.impl;

import com.gas.station.model.Service;
import com.gas.station.model.enums.ServiceType;
import com.gas.station.repository.ServiceRepository;
import com.gas.station.service.feed.FeedingService;
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
            Service newService = new Service();
            newService.setType(serviceType);
            servicesForSave.add(newService);
        }
        repository.save(servicesForSave);
        return servicesForSave.size();
    }
}
