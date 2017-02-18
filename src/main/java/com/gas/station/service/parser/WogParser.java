package com.gas.station.service.parser;

import com.gas.station.exception.ElementParseException;
import com.gas.station.model.Address;
import com.gas.station.model.Fuel;
import com.gas.station.model.Service;
import com.gas.station.model.enums.FuelType;
import com.gas.station.model.enums.ServiceType;
import com.gas.station.service.WogFuelPriceService;
import lombok.extern.log4j.Log4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.gas.station.model.enums.BrandType.WOG;
import static java.util.stream.Collectors.toList;

@Log4j
@Component
public class WogParser extends GasStationParser<Element> {

    private static final String ITEM_SELECTOR = "li.driver_map_item";
    private static final String INFO_SELECTOR = "div.info";
    private static final String ID_SELECTOR = "div.info span";
    private static final String SERVICES_SELECTOR = "div.services p";
    private static final String LAT_CLASS = "lat";
    private static final String LNG_CLASS = "lng";
    private static final String FUELS_CLASS = "fuels";
    private static final String FUEL_SEPARATOR = ",";

    //TODO make it configurable
    @Value("${wog.station.list:https://wog.ua/ru/map/list/}")
    private String gasStationListUrl = "https://wog.ua/ru/map/list/";
    @Value("${read.timeout:60000}")
    private int timeOut;

    @Autowired
    private WogFuelPriceService priceService;

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
        Map<FuelType, String> fuelPrices = priceService.getFuelPrices(parseInnerId(item));
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
