package com.gas.station.service.parser;

import com.gas.station.model.GasStation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;


//TODO write it
@RunWith(SpringRunner.class)
@SpringBootTest
public class WogParserIT {

    private static final int STATION_COUNT = 411;

    @Autowired
    private WogParser wogParser;

    @Test
    public void shouldSuccesfullyGetOriginalGasStations() throws Exception {
        //GIVEN
        //WHEN
        List<GasStation> gasStations = wogParser.parseGasStations();
        //THEN
        assertThat(gasStations, hasSize(STATION_COUNT));
    }

}