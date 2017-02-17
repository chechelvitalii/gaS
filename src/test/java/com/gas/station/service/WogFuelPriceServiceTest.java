package com.gas.station.service;

import com.gas.station.model.enums.FuelType;
import com.google.common.collect.ImmutableMap;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsMapContaining;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.testng.junit.JUnit4TestRunner;

import java.util.Map;

import static com.gas.station.TestUtils.getHtmlByPath;
import static com.gas.station.model.enums.FuelType.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class WogFuelPriceServiceTest {

    private static final String FUEL_AND_PRICES_RESOURCE_PATH = "/wog/fuelAndPrices.html";

    @InjectMocks
    private WogFuelPriceService priceService;
    @Mock
    private RestTemplate restClient;

    @Test
    public void shouldSuccessfullyGetFuelPrices() throws Exception {
        //GIVEN
        Map<FuelType, String> expectedFuelPrices = getFuelPrices();
        when(restClient.postForObject(anyString(), any(LinkedMultiValueMap.class), any(Class.class)))
                .thenReturn(getHtmlByPath(FUEL_AND_PRICES_RESOURCE_PATH));
        //WHEN
        Map<FuelType, String> actualFuelPrices = priceService.getFuelPrices(100500);
        //THEN
        assertThat(actualFuelPrices, Matchers.equalTo(expectedFuelPrices));

    }

    private Map<FuelType, String> getFuelPrices() {
        return ImmutableMap.of(A_95, "26,99", MUSTANG_92, "25,99",
                MUSTANG_DT, "23,99", MUSTANG_DT_PLUS, "24,99");
    }

}