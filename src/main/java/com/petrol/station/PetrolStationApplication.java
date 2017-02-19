package com.petrol.station;

import com.petrol.station.model.Address;
import com.petrol.station.model.PetrolStation;
import com.petrol.station.model.enums.BrandType;
import com.petrol.station.repository.PetrolStationRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Log4j
@SpringBootApplication
public class PetrolStationApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetrolStationApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Bean
    public CommandLineRunner demo(PetrolStationRepository petrolStationRepository) {
        return (args) -> {
            PetrolStation petrolStation = new PetrolStation();
            petrolStation.setBrandType(BrandType.WOG);
            petrolStation.setInnerId(100500);
            Address address = new Address();
            address.setStreet("Street");
            address.setCity("city");
            address.setLat("11");
            address.setLng("22");
//            Service service = new Service(ServiceType.WASHING);
//            petrolStation.setServices(Arrays.asList(service));
            petrolStation.setAddress(address);
            log.info("Try save Petrol Station");
            PetrolStation savedGs = petrolStationRepository.save(petrolStation);
            log.info(savedGs.toString());
            log.info("Get all Petrol Stations");
            List<PetrolStation> all = petrolStationRepository.findAll();
            log.info(all.toString());

        };
    }
}
