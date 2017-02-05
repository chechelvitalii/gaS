package com.gas.station;

import com.gas.station.facade.FeedingFacade;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
//        WogParser wogParser = new WogParser();
//        List<GasStation> gasStations = wogParser.parseGasStations();
//        System.out.println(gasStations);

        ApplicationContext context =
                new AnnotationConfigApplicationContext(GasStationApplication.class);
        FeedingFacade feedingFacade = context.getBean(FeedingFacade.class);
        feedingFacade.performStaticFeeding();
    }
}
