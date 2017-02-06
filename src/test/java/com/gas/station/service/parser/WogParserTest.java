package com.gas.station.service.parser;

import com.gas.station.model.Address;
import com.gas.station.model.Fuel;
import com.gas.station.model.GasStation;
import com.google.common.io.Resources;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;

//TODO parametrise tests
@RunWith(PowerMockRunner.class)
@PrepareForTest(Jsoup.class)
public class WogParserTest {

    private static final String WOG_FULL_LIST_RESOURCE_PATH = "/wog/wogFullList.html";
    private static final int WOG_STATION_COUNT = 411;

    private static final String WOG_TEST_LIST_RESOURCE_PATH = "/wog/wogTestList.html";
    public static final Integer TEST_INNER_ID = 51157;
    @Mock
    private WogParser parser = new WogParser();

    private Document fullDomHtml;
    private Document testDomHtml;

    @Before
    public void setUP() throws IOException {
        URL resource = getClass().getResource(WOG_FULL_LIST_RESOURCE_PATH);
        String fullHtml = Resources.toString(resource, UTF_8);
        fullDomHtml = Jsoup.parse(fullHtml);

        URL testResource = getClass().getResource(WOG_TEST_LIST_RESOURCE_PATH);
        String testHtml = Resources.toString(testResource, UTF_8);
        testDomHtml = Jsoup.parse(testHtml);

        PowerMockito.mockStatic(Jsoup.class);
    }

    @Test
    public void shouldSuccessfullyParseCountAddress() throws Exception {
        //GIVEN
        PowerMockito.when(Jsoup.parse(any(URL.class), any(Integer.class))).thenReturn(fullDomHtml);
        //WHEN
        List<Address> addresses = new ArrayList<>();
        for (Element element : parser.getOriginalGasStations()) {
            addresses.add(parser.parseAddress(element));
        }
        //THEN
        assertThat(addresses, hasSize(WOG_STATION_COUNT));
    }

    @Test
    public void shouldSuccessfullyParseFuels() throws Exception {
        //GIVEN
        PowerMockito.when(Jsoup.parse(any(URL.class), any(Integer.class))).thenReturn(testDomHtml);
        //WHEN
        List<Fuel> fuels = new ArrayList<>();
        for (Element element : parser.getOriginalGasStations()) {
            fuels.addAll(parser.parseFuels(element));
        }
        //THEN
        assertThat(fuels, hasSize(4));
    }

    @Test
    public void pshouldSuccessfullyParseServices() throws Exception {
//        for (Element element : parser.getOriginalGasStations()) {
//            List<Service> services = parser.parseServices(element);
//            assertFalse(services.isEmpty());
//        }
    }

    @Test
    public void shouldSuccessfullyParseInnerIds() throws Exception {
        //GIVEN
        PowerMockito.when(Jsoup.parse(any(URL.class), any(Integer.class))).thenReturn(testDomHtml);
        //WHEN
        List<Integer> innerIds = new ArrayList<>();
        for (Element element : parser.getOriginalGasStations()) {
            innerIds.add(parser.parseInnerId(element));
        }
        //THEN
        assertThat(innerIds, hasSize(1));
        assertThat(innerIds, hasItem(TEST_INNER_ID));
    }


    @Test
    public void shouldSuccessfullyParseGasStations() throws Exception {
        //GIVEN
        PowerMockito.when(Jsoup.parse(any(URL.class), any(Integer.class))).thenReturn(fullDomHtml);
        //WHEN
        List<GasStation> gasStations = parser.parseGasStations();
        //THEN
        assertThat(gasStations, hasSize(WOG_STATION_COUNT));

    }

    @Test
    public void shouldReturnOriginalGasStations() throws Exception {
        //GIVEN
        PowerMockito.when(Jsoup.parse(any(URL.class), any(Integer.class))).thenReturn(fullDomHtml);
        //WHEN
        List<Element> originalGasStations = parser.getOriginalGasStations();
        //THEN
        assertThat("Count of parsed origin gas station wrong", originalGasStations, hasSize(WOG_STATION_COUNT));
    }

    private List<Fuel> getTestFuelList(){
        List<Fuel> fuels = new ArrayList<>();


        return fuels;
    }
}