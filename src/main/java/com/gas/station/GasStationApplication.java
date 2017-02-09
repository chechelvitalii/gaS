package com.gas.station;

import com.gas.station.model.Address;
import com.gas.station.model.GasStation;
import com.gas.station.model.enums.BrandType;
import com.gas.station.repository.GasStationRepository;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class GasStationApplication {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(GasStationApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GasStationApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(GasStationRepository gasStationRepository) {
        return (args) -> {
            GasStation gasStation = new GasStation();
            gasStation.setBrandType(BrandType.WOG);
            gasStation.setInnerId(100500);
            Address address = new Address();
            address.setStreet("Street");
            address.setCity("city");
            address.setLat("11");
            address.setLng("22");

            gasStation.setAddress(address);
            log.info("Try save Gas Station");
            GasStation savedGs = gasStationRepository.save(gasStation);
            log.info(savedGs.toString());
            log.info("Get all Gas Stations");
            List<GasStation> all = gasStationRepository.findAll();
            log.info(all.toString());

        };
    }
}
