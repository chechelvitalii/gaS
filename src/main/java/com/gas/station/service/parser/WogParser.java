package com.gas.station.service.parser;

import com.gas.station.HtmlValidator;
import com.gas.station.exception.ElementParseException;
import com.gas.station.model.Address;
import com.gas.station.model.Fuel;
import com.gas.station.model.Service;
import com.gas.station.model.enums.FuelType;
import com.gas.station.model.enums.ServiceType;
import com.google.common.annotations.VisibleForTesting;
import lombok.extern.log4j.Log4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static com.gas.station.model.enums.BrandType.WOG;
import static java.util.stream.Collectors.toList;

@Log4j
@Component
public class WogParser extends GasStationParser<Element> {

    @VisibleForTesting
    public static final String ITEM_SELECTOR = "li.driver_map_item";
    private static final String INFO_SELECTOR = "div.info";
    private static final String ID_SELECTOR = "div.info span";
    private static final String SERVICES_SELECTOR = "div.services p";
    private static final String LAT_CLASS = "lat";
    private static final String LNG_CLASS = "lng";
    private static final String FUELS_CLASS = "fuels";
    private static final String FUEL_SEPARATOR = ",";
    private static final String FUELS_AND_PRICES = "div#cost.map_pane.act ul.map_cost_list li";

    private static final String STATION_ID = "STATION_ID";
    private static final String PRICE = "price";
    private static final String IMG_LOGO = "span.cost_img_container span.cost_img_container_inner img.logo";
    private static final String SRC = "src";

    @Autowired
    private RestTemplate restClient;

    //TODO make it configurable
    @Value("${wog.station.list:https://wog.ua/ua/map/list/}")
    private String gasStationListUrl = "https://wog.ua/ua/map/list/";
    //TODO make it configurable
    @Value("${wog.station.fuelPrices.and.services:https://wog.ua/drivers/get-gaz-station-detail}")
    private String gasStationFuelPricesUrl = "https://wog.ua/drivers/get-gaz-station-detail";
    @Value("${read.timeout:60000}")
    private int timeOut;

    public WogParser() {
        super(WOG);
    }

    @Override
    protected Address parseAddress(Element item) {
        String lat = item.getElementsByClass(LAT_CLASS).val();
        String lng = item.getElementsByClass(LNG_CLASS).val();
        Element info = item.select(INFO_SELECTOR).first();
        String city = info.select("h3").text();
        //TODO refactoring need
        List<String> mayBeStreet = info.textNodes().stream()
                .map(textNode -> textNode.text().trim())
                .filter(text -> !text.isEmpty())
                .collect(toList());
        if (mayBeStreet.size() != 1) {
            throw new ElementParseException("Can't parse street:" + mayBeStreet);
        }
        Address address = new Address();
        address.setLat(lat);
        address.setLng(lng);
        address.setCity(city);
        address.setStreet(mayBeStreet.get(0));
        return address;

    }

    @Override
    protected List<Fuel> parseFuels(Element item) {
        Map<FuelType, String> fuelPrices = getFuelPrices(parseInnerId(item));
        //A-95, 92 MUSTANG, ДП MUSTANG, ДП MUSTANG+
        String fuelBlock = item.getElementsByClass(FUELS_CLASS).stream()
                .findFirst()
                .orElseThrow(() -> new ElementParseException("Not found class=\"fuels\" in parse element"))
                .text();
        return Arrays.stream(fuelBlock.split(FUEL_SEPARATOR))
                .map(String::trim)
                .map(fuelName -> FuelType.getFuelTypeByName(fuelName))
                .map(fuelType -> new Fuel(fuelType, fuelPrices.get(fuelType)))
                .collect(toList());
    }

    private Map<FuelType, String> getFuelPrices(int id) {
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

    @Override
    protected List<Service> parseServices(Element item) {
        Elements originalServices = item.select(SERVICES_SELECTOR);
        return originalServices.stream()
                .map(element -> new Service(ServiceType.getTypeByName(element.text())))
                .collect(toList());
    }


    @Override
    protected int parseInnerId(Element item) {
        return Integer.parseInt(item.select(ID_SELECTOR).attr("data-id"));
    }

    @Override
    protected List<Element> getOriginalGasStations() throws IOException {
        Document domHtml = Jsoup.parse(new URL(gasStationListUrl), timeOut);
        return domHtml.select(ITEM_SELECTOR);
    }
}
