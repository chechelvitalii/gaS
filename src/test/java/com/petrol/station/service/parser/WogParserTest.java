package com.petrol.station.service.parser;

import com.petrol.station.model.Address;
import com.petrol.station.model.Fuel;
import com.petrol.station.model.Service;
import com.petrol.station.model.enums.FuelType;
import com.petrol.station.service.WogFuelPriceService;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Resources;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.petrol.station.TestUtils.*;
import static com.petrol.station.model.enums.FuelType.*;
import static com.petrol.station.model.enums.ServiceType.*;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Jsoup.class)
public class WogParserTest {

    private static final String FULL_LIST_PETROL_STATION_RESOURCE_PATH = "/wog/fullListPetrolStations.html";

    private static final String LIST_WITH_ONE_PETROL_STATION_RESOURCE_PATH = "/wog/listWithOnePetrolStation.html";
    private static final Integer TESTED_INNER_ID = 51573;

    @InjectMocks
    private WogParser parser;
    @Mock
    private WogFuelPriceService priceService;
    private Document fullDomHtml;
    private Element originalPetrolStationsElement;


    @Before
    public void setUP() throws IOException {
        URL resource = getClass().getResource(FULL_LIST_PETROL_STATION_RESOURCE_PATH);
        String fullHtml = Resources.toString(resource, UTF_8);
        fullDomHtml = Jsoup.parse(fullHtml);

        Document domWithOnePetrolStation = Jsoup.parse(getHtmlByPath(LIST_WITH_ONE_PETROL_STATION_RESOURCE_PATH));
        String item_selector = (String) ReflectionTestUtils.getField(WogParser.class, "ITEM_SELECTOR");
        originalPetrolStationsElement = domWithOnePetrolStation.select(item_selector).get(0);
    }

    @Test
    public void shouldSuccessfullyParseAddress() throws Exception {
        //WHEN
        Address address = parser.parseAddress(originalPetrolStationsElement);
        //THEN
        assertThat(address, is(getTestAddress()));
    }

    @Test
    public void shouldSuccessfullyParseFuels() throws Exception {
        //GIVEN
        when(priceService.getFuelPrices(TESTED_INNER_ID)).thenReturn(getTestFuelPrices());
        //WHEN
        List<Fuel> fuels = parser.parseFuels(originalPetrolStationsElement);
        //THEN
        assertThat(fuels, hasSize(4));
        assertThat(fuels, contains(getTestFuels().toArray()));
    }

    @Test
    public void shouldSuccessfullyParseServices() throws Exception {
        //WHEN
        List<Service> services = parser.parseServices(originalPetrolStationsElement);
        //THEN
        assertThat(services, hasSize(5));
        assertThat(services, contains(getTesServices().toArray()));
    }

    @Test
    public void shouldSuccessfullyParseInnerIds() throws Exception {
        //WHEN
        int innerId = parser.parseInnerId(originalPetrolStationsElement);
        //THEN
        assertThat(innerId, is(TESTED_INNER_ID));
    }

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

    private Map<FuelType, String> getTestFuelPrices() {
        return ImmutableMap.of(A_95, "26,99", MUSTANG_92, "25,99",
                MUSTANG_DT, "23,99", MUSTANG_DT_PLUS, "24,99");
    }
}