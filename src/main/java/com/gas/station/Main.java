package com.gas.station;

import com.gas.station.parser.ParserWOG;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ParserWOG parserWOG = new ParserWOG();
        parserWOG.parseGasStation();
    }
}
