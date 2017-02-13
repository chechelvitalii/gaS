package com.gas.station;

import com.gas.station.model.Address;
import com.gas.station.model.GasStation;
import com.gas.station.model.Service;
import com.gas.station.model.enums.BrandType;
import com.gas.station.model.enums.ServiceType;
import com.gas.station.repository.GasStationRepository;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Log4j
@SpringBootApplication
public class GasStationApplication {

    public static void main(String[] args) {
        SpringApplication.run(GasStationApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
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
//            Service service = new Service(ServiceType.WASHING);
//            gasStation.setServices(Arrays.asList(service));
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
