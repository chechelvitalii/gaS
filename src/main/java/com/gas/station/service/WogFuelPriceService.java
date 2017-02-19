package com.gas.station.service;

import com.gas.station.HtmlValidator;
import com.gas.station.model.enums.FuelType;
import lombok.extern.log4j.Log4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Log4j
@Component
public class WogFuelPriceService {
    private static final String FUELS_AND_PRICES = "div#cost.map_pane.act ul.map_cost_list li";

    private static final String STATION_ID = "id";
    private static final String PRICE = "price";
    private static final String IMG_LOGO = "span.cost_img_container span.cost_img_container_inner img.logo";
    private static final String SRC = "src";

    @Value("${station.wog.fuel.prices}")
    private String gasStationFuelPricesUrl;

    @Autowired
    private RestTemplate restClient;

    public Map<FuelType, String> getFuelPrices(int id) {
        Map<FuelType, String> fuelAndPrices = new HashMap<>();
        String validHtml = HtmlValidator.validate(getFuelPricesHtml(id));
        Elements fuelAndPricesElements = Jsoup.parse(validHtml).select(FUELS_AND_PRICES);
        for (Element fuelAndPrice : fuelAndPricesElements) {
            String price = fuelAndPrice.getElementsByClass(PRICE).text();
            String detectValue = fuelAndPrice.select(IMG_LOGO).attr(SRC);
            log.info("Price:" + price + ", Detect Value(ImgLogo):" + detectValue);
            fuelAndPrices.put(FuelType.getFuelTypeByDetectValue(detectValue), price);
        }
        return fuelAndPrices;
    }

    private String getFuelPricesHtml(int id) {
        MultiValueMap<String, Object> uriVars = new LinkedMultiValueMap();
        uriVars.put(STATION_ID, Collections.singletonList(id));
        return restClient.postForObject(gasStationFuelPricesUrl, uriVars, String.class);
    }
}
