package com.petrol.station.service.parser;

import com.petrol.station.model.Address;
import com.petrol.station.model.Fuel;
import com.petrol.station.model.PetrolStation;
import com.petrol.station.model.Service;
import com.petrol.station.model.enums.BrandType;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j
public abstract class PetrolStationParser<T> {
    private BrandType brandType;

    @Value("${read.timeout:60000}")
    private int timeOut;

    public PetrolStationParser(BrandType brandType) {
        this.brandType = brandType;
    }

    protected abstract Address parseAddress(T item);

    protected abstract List<Fuel> parseFuels(T item);

    protected abstract List<Service> parseServices(T item);

    protected abstract int parseInnerId(T item);

    protected abstract List<T> getOriginalPetrolStations() throws IOException;

    public List<PetrolStation> parsePetrolStations() {
        List<PetrolStation> parsedPetrolStations = new ArrayList<>();
        try {
            for (T originalPetrolStation : getOriginalPetrolStations()) {
                PetrolStation petrolStation = PetrolStation.builder()
                        .address(parseAddress(originalPetrolStation))
                        .fuels(parseFuels(originalPetrolStation))
                        .services(parseServices(originalPetrolStation))
                        .innerId(parseInnerId(originalPetrolStation))
                        .brandType(brandType)
                        .build();
                parsedPetrolStations.add(petrolStation);
            }
        } catch (IOException ex) {
            log.error("Problem due get original petrol station", ex);
            throw new RuntimeException(ex);
        }
        return parsedPetrolStations;
    }
}
