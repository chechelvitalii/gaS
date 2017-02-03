package com.gas.station;

import com.gas.station.model.GasStation;
import com.gas.station.parser.GasStationParser;
import com.gas.station.parser.ParserWOG;
import com.gas.station.parser.impl.WogParser;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        WogParser wogParser = new WogParser();
        List<GasStation> gasStations = wogParser.parseGasStations();
        System.out.println(gasStations);
    }
}
