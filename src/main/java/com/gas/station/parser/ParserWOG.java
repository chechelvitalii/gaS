package com.gas.station.parser;


import org.springframework.stereotype.Component;

@Component
public class ParserWOG {//extends GasStationParser {

    private static final String ITEM_CLASS = "driver_map_item";
    private static final String SERVICES_SELECTOR = "div.services p";
    private static final String INFO_SELECTOR = "div.info";
    private static final String LAT_CLASS = "lat";
    private static final String LNG_CLASS = "lng";

//    public ParserWOG() {
//        super.GAS_STATION_LIST_URL = "https://wog.ua/ua/map/list/";
//    }

//    @Override
//    public void parseGasStation() throws IOException {
//        Document wogDocument = getDocument();
//
//        Elements items = wogDocument.getElementsByClass(ITEM_CLASS);
//        for (Element item : items) {
//            System.out.println("###############");
//            Elements services = item.select(SERVICES_SELECTOR);
//            services.forEach(i -> System.out.println(i.text()));
//            String lat = item.getElementsByClass(LAT_CLASS).val();
//            String lng = item.getElementsByClass(LNG_CLASS).val();
//            Element info = item.select(INFO_SELECTOR).first();
//            List<String> mayBeStreet = info.textNodes().stream()
//                    .map(textNode -> textNode.text().trim())
//                    .filter(text -> !text.isEmpty())
//                    .collect(toList());
//            if (mayBeStreet.size() != 1) {
//                throw new ParseStreetException("Expected one street but found " + mayBeStreet.size());
//            }
//            String street = mayBeStreet.get(0);
//            String town = info.select("h3").text();
//            String id = info.select("span").attr("data-id");
//            System.out.println(id + "\n" + town + "\n" + street+"\n"+lat+"\n"+lng);
//            System.out.println("###############");
//
//        }
//    }
}
