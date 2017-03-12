package com.petrol.station.service.parser;

import com.petrol.station.model.PetrolStation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class WogParserIT {

    private static final int STATION_COUNT = 411;

    @Autowired
    private WogParser wogParser;

    @Test
    public void shouldSuccessfullyParsePetrolStations() throws Exception {
        //WHEN
        List<PetrolStation> petrolStations = wogParser.parsePetrolStations();
        //THEN
        assertThat(petrolStations, hasSize(STATION_COUNT));
    }

}