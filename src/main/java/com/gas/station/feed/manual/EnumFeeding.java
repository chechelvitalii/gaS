package com.gas.station.feed.manual;

import com.gas.station.model.Brand;
import com.gas.station.model.Fuel;
import com.gas.station.model.Service;
import com.gas.station.model.enums.BrandType;
import com.gas.station.model.enums.FuelType;
import com.gas.station.model.enums.ServiceType;
import com.gas.station.repository.BrandRepository;
import com.gas.station.repository.FuleRepository;
import com.gas.station.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnumFeeding {


    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private FuleRepository fuleRepository;
    @Autowired
    private ServiceRepository serviceRepository;

    public void feeding() {
        BrandType[] brandTypes = BrandType.values();
        for (BrandType brandType : brandTypes) {
            Brand brand = new Brand();
            brand.setType(brandType);
            brandRepository.save(brand);
        }

        FuelType[] fuelTypes = FuelType.values();
        for (FuelType fuelType : fuelTypes) {
            Fuel fuel = new Fuel();
            fuel.setType(fuelType);
            fuleRepository.save(fuel);
        }

        ServiceType[] serviceTypes = ServiceType.values();
        for (ServiceType serviceType : serviceTypes) {
            Service service = new Service();
            service.setType(serviceType);
            serviceRepository.save(service);
        }
    }
}
