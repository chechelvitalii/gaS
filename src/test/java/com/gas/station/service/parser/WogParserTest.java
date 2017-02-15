package com.gas.station.service.parser;

import com.gas.station.model.Address;
import com.gas.station.model.Fuel;
import com.gas.station.model.Service;
import com.google.common.io.Resources;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static com.gas.station.model.enums.FuelType.*;
import static com.gas.station.model.enums.ServiceType.*;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(SpringRunner.class)
public class WogParserTest {

    private static final String WOG_FULL_LIST_RESOURCE_PATH = "/wog/wogFullList.html";
    private static final int WOG_STATION_COUNT = 411;

    private static final String LIST_WITH_ONE_GAS_STATION_RESOURCE_PATH = "/wog/listWithOneGasStation.html";
    private static final Integer TESTED_INNER_ID = 51573;

    private static final String WOG_FUEL_AND_PRICES_RESOURCE_PATH = "/wog/fuelAndPrices.html";

    @InjectMocks
    private WogParser parser;
    @Mock
    private RestTemplate restClient;
    //TODO it should be IT
//    private Document fullDomHtml;

    Elements originalGasStationsElements;


    @Before
    public void setUP() throws IOException {
//        URL resource = getClass().getResource(WOG_FULL_LIST_RESOURCE_PATH);
//        String fullHtml = Resources.toString(resource, UTF_8);
//        fullDomHtml = Jsoup.parse(fullHtml);


        Document domWithOneGasStation = Jsoup.parse(getHtmlByPath(LIST_WITH_ONE_GAS_STATION_RESOURCE_PATH));
        originalGasStationsElements = domWithOneGasStation.select(WogParser.ITEM_SELECTOR);


        PowerMockito.mockStatic(Jsoup.class);

    }

    @Test
    public void shouldSuccessfullyParseAddress() throws Exception {
        //GIVEN
        Element originalGasStations = originalGasStationsElements.get(0);
        //WHEN
        Address address = parser.parseAddress(originalGasStations);
        //THEN
        assertThat(address, is(getTestAddress()));
    }

    @Test
    public void shouldSuccessfullyParseFuels() throws Exception {
        //GIVEN
        Element originalGasStations = originalGasStationsElements.get(0);
        when(restClient.postForObject(anyString(), any(LinkedMultiValueMap.class), any(Class.class)))
                .thenReturn(getHtmlByPath(WOG_FUEL_AND_PRICES_RESOURCE_PATH));
        //WHEN
        List<Fuel> fuels = parser.parseFuels(originalGasStations);
        //THEN
        assertThat(fuels, hasSize(4));
        assertThat(fuels, contains(getTestFuels().toArray()));
    }

    @Test
    public void shouldSuccessfullyParseServices() throws Exception {
        //GIVEN
        Element originalGasStations = originalGasStationsElements.get(0);
        //WHEN
        List<Service> services = parser.parseServices(originalGasStations);
        //THEN
        assertThat(services, hasSize(5));
        assertThat(services, contains(getTesServices().toArray()));
    }

    @Test
    public void shouldSuccessfullyParseInnerIds() throws Exception {
        //GIVEN
        Element originalGasStations = originalGasStationsElements.get(0);
        //WHEN
        int innerId = parser.parseInnerId(originalGasStations);
        //THEN
        assertThat(innerId, is(TESTED_INNER_ID));
    }

//    @Test
//    public void shouldSuccessfullyParseGasStations() throws Exception {
//        //GIVEN
//        PowerMockito.when(Jsoup.parse(any(URL.class), any(Integer.class))).thenReturn(fullDomHtml);
//        //WHEN
//        List<GasStation> gasStations = parser.parseGasStations();
//        //THEN
//        assertThat(gasStations, hasSize(WOG_STATION_COUNT));
//    }


//    @Test
//    public void shouldReturnOriginalGasStations() throws Exception {
//        //GIVEN
//        PowerMockito.when(Jsoup.parse(any(URL.class), any(Integer.class))).thenReturn(fullDomHtml);
//        //WHEN
//        List<Element> originalGasStations = parser.getOriginalGasStations();
//        //THEN
//        assertThat("Count of parsed origin gas station wrong", originalGasStations, hasSize(WOG_STATION_COUNT));
//    }

    private List<Fuel> getTestFuels() {
        return Arrays.asList(new Fuel(A_95, "26,99"),
                new Fuel(MUSTANG_92, "25,99"),
                new Fuel(MUSTANG_DT, "23,99"),
                new Fuel(MUSTANG_DT_PLUS, "24,99")
        );
    }

    private Address getTestAddress() {
        Address testAddress = new Address();
        testAddress.setCity("м. Луцьк");
        testAddress.setStreet("Конякіна 22");
        testAddress.setLat("50.76532");
        testAddress.setLng("25.355281");
        return testAddress;
    }

    private List<Service> getTesServices() {
        return Arrays.asList(
                new Service(PHONE),
                new Service(COUPONS),
                new Service(CREDIT_CARDS),
                new Service(FUEL_CARDS),
                new Service(INSURANCE)
        );
    }

    private String getHtmlByPath(String path) throws IOException {
        URL url = getClass().getResource(path);
        return Resources.toString(url, UTF_8);
    }
}