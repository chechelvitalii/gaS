package com.petrol.station;

import com.petrol.station.model.PetrolStation;
import com.petrol.station.repository.PetrolStationRepository;
import com.petrol.station.service.parser.WogParser;
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
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Bean
    public CommandLineRunner demo(PetrolStationRepository petrolStationRepository, WogParser wogParser) {
        return (args) -> {
            List<PetrolStation> petrolStations = wogParser.parsePetrolStations();

            List<PetrolStation> save = petrolStationRepository.save(petrolStations);
            log.info("Get all Petrol Stations");
            List<PetrolStation> all = petrolStationRepository.findAll();
            log.info(all.toString());

        };
    }
}
