package com.gas.station;

import com.gas.station.facade.FeedingFacade;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;
import org.h2.result.ResultTempTable;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.EMPTY;

public class Main {
    public static void main(String[] args) throws IOException {
//        WogParser wogParser = new WogParser();
//        List<GasStation> gasStations = wogParser.parseGasStations();
//        System.out.println(gasStations);

//        ApplicationContext context =
//                new AnnotationConfigApplicationContext(GasStationApplication.class);
//        FeedingFacade feedingFacade = context.getBean(FeedingFacade.class);
//        feedingFacade.performStaticFeeding();


//        // example how to send post with Jsoup = headers
//        String path = "https://wog.ua/drivers/get-gaz-station-detail?id=52365";
//        URL url = new URL(path);
//        Connection connect = Jsoup.connect(path);
//        connect.header("X-Requested-With", "XMLHttpRequest");
//        connect.header("Content-Type", "utf-8");
//        Document post = connect.post();
//        Document parse = Jsoup.parse(url, 10000);
//        System.out.println(post);


        String url = "https://wog.ua/drivers/get-gaz-station-detail";
        MultiValueMap<String, String> uriVars = new LinkedMultiValueMap();
        uriVars.put("id", Collections.singletonList("52365"));
        RestTemplate restTemplate = new RestTemplate();
        String postForObject = restTemplate.postForObject(url, uriVars, String.class);


    }
}
