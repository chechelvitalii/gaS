package com.gas.station;

import com.gas.station.facade.FeedingFacade;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {
//        WogParser wogParser = new WogParser();
//        List<GasStation> gasStations = wogParser.parseGasStations();
//        System.out.println(gasStations);

//        ApplicationContext context =
//                new AnnotationConfigApplicationContext(GasStationApplication.class);
//        FeedingFacade feedingFacade = context.getBean(FeedingFacade.class);
//        feedingFacade.performStaticFeeding();


        // example how to send post with Jsoup = headers
        String path = "https://wog.ua/drivers/get-gaz-station-detail?id=52365";
        URL url = new URL(path);
        Connection connect = Jsoup.connect(path);
        connect.header("X-Requested-With", "XMLHttpRequest");
        connect.header("Content-Type", "utf-8");
        Document post = connect.post();
        Document parse = Jsoup.parse(url, 10000);
        System.out.println(parse);


    }
}
