package com.gas.station.service.parser;

import com.gas.station.exception.ParseStreetException;
import com.gas.station.model.Address;
import com.gas.station.model.Fuel;
import com.gas.station.model.Service;

import com.gas.station.model.enums.ServiceType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.gas.station.model.enums.BrandType.WOG;
import static java.util.stream.Collectors.toList;

public class WogParser extends GasStationParser<Element> {

    private static final String ITEM_SELECTOR = "li.driver_map_item";
    private static final String INFO_SELECTOR = "div.info";
    private static final String ID_SELECTOR = "div.info span";
    private static final String LAT_CLASS = "lat";
    private static final String LNG_CLASS = "lng";
    private static final String SERVICES_SELECTOR = "div.services p";


    //TODO make it configurable
    @Value("${wog.station.list:https://wog.ua/ua/map/list/}")
    private String gasStationListUrl = "https://wog.ua/ua/map/list/";
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
            throw new ParseStreetException("Expected one street but found " + mayBeStreet.size());
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
        return null;
    }

    @Override
    protected List<Service> parseServices(Element item) {
        List<Service> services = new ArrayList<>();
        Elements originalServices = item.select(SERVICES_SELECTOR);
        for (Element originalService : originalServices) {
            Service service = new Service();
            service.setType(ServiceType.getTypeByName(originalService.text()));
            services.add(service);
        }
        return services;
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
