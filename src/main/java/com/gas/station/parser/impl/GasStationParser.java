package com.gas.station.parser.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.URL;

public abstract class GasStationParser {
    protected String GAS_STATION_LIST_URL;

    @Value("${timeout:60000}")
    private int timeOut;

    public abstract void parseGasStation() throws IOException;

    protected Document getDocument() throws IOException {
        return Jsoup.parse(new URL(GAS_STATION_LIST_URL), timeOut);
    }
}
