package com.gas.station.parser;

import com.gas.station.model.Address;
import com.gas.station.model.enums.BrandType;
import com.gas.station.model.Fuel;
import com.gas.station.model.GasStation;
import com.gas.station.model.Service;

import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class GasStationParser<T> {
    private BrandType brandType;

    @Value("${read.timeout:60000}")
    private int timeOut;

    public GasStationParser(BrandType brandType) {
        this.brandType = brandType;
    }

    protected abstract void parseAddress(Address address, T item);

    protected abstract List<Fuel> parseFuel(T item);

    protected abstract List<Service> parseService(T item);

    protected abstract int parseInnerId(T item);

    protected abstract List<T> getOriginalGasStations() throws IOException;

    public List<GasStation> parseGasStations() throws IOException {
        List<GasStation> parsedGasStations = new ArrayList<>();
        for (T originalGasStation : getOriginalGasStations()) {
            Address address = new Address();

            parseAddress(address, originalGasStation);

            GasStation gasStation = GasStation.builder()
                    .address(address)
                    .fuels(parseFuel(originalGasStation))
                    .services(parseService(originalGasStation))
                    .innerId(parseInnerId(originalGasStation))
                    .brandType(brandType)
                    .build();
            parsedGasStations.add(gasStation);
        }
        return parsedGasStations;
    }
}
