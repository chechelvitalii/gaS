package com.gas.station.service.parser;

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
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;

//TODO parametrise tests
@RunWith(PowerMockRunner.class)
@PrepareForTest(Jsoup.class)
public class WogParserTest {

    private static String WOG_LIST_RESOURCE_PATH = "/wogList.html";

    @Mock
    private WogParser parser = new WogParser();

    @Before
    public void setUP() throws IOException {
        URL resource = getClass().getResource(WOG_LIST_RESOURCE_PATH);
        String testHtml = Resources.toString(resource, UTF_8);
        Document domHtml = Jsoup.parse(testHtml);

        PowerMockito.mockStatic(Jsoup.class);
        PowerMockito.when(Jsoup.parse(any(URL.class), any(Integer.class))).thenReturn(domHtml);
    }

    @Test
    public void parseAddress() throws Exception {

    }

    @Test
    public void parseFuel() throws Exception {

    }

    @Test
    public void parseService() throws Exception {

    }

    @Test
    public void parseInnerId() throws Exception {
        for (Element element : parser.getOriginalGasStations()) {
            int i = parser.parseInnerId(element);
            //TODO add @Capture
        }
    }



    @Test

    public void parseGasStations() throws Exception {
    }

    @Test
    public void shouldReturnOriginalGasStations() throws Exception {
        //WHEN
        List<Element> originalGasStations = parser.getOriginalGasStations();
        //THEN
        assertThat("Count of parsed origin gas station wrong", originalGasStations, hasSize(411));
    }

}