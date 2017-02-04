package com.gas.station;

import com.gas.station.feed.manual.EnumFeeding;
import com.gas.station.model.GasStation;
import com.gas.station.parser.GasStationParser;
import com.gas.station.parser.ParserWOG;
import com.gas.station.parser.impl.WogParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
//        WogParser wogParser = new WogParser();
//        List<GasStation> gasStations = wogParser.parseGasStations();
//        System.out.println(gasStations);

        ApplicationContext context =
                new AnnotationConfigApplicationContext(GasStationApplication.class);
        EnumFeeding enumFeeding = context.getBean(EnumFeeding.class);
enumFeeding.feeding();
    }
}
