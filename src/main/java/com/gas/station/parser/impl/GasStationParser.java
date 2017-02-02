package com.gas.station.parser.impl;

import com.gas.station.model.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public abstract class GasStationParser {
    private String gasStationListUrl;
    private Brand brand;

    @Value("${read.timeout:60000}")
    private int timeOut;

    public GasStationParser(String url,Brand brand) {
        this.gasStationListUrl = url;
        this.brand = brand;
    }

    public abstract Address parseAddress();

    public abstract List<Fuel> parseFuel();

    public abstract List<Service> parseService();

    public abstract int parseInnerId();


    public GasStation parseGasStation() throws IOException {
        GasStation parsedGasStation = new GasStation();
        parsedGasStation.setAddress(parseAddress());
        parsedGasStation.setFuels(parseFuel());
        parsedGasStation.setServices(parseService());
        parsedGasStation.setBrand(brand);
        parsedGasStation.setInnerId(parseInnerId());
        return parsedGasStation;
    }

    protected Document getDocument() throws IOException {
        return Jsoup.parse(new URL(gasStationListUrl), timeOut);
    }
}
