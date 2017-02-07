package com.gas.station.model.enums;

import com.gas.station.exception.ConvertTypeException;
import org.junit.Test;

import static com.gas.station.model.enums.FuelType.A_95;
import static com.gas.station.model.enums.FuelType.getFuelTypeByName;
import static com.gas.station.model.enums.ServiceType.WASHING;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ServiceTypeTest {
    @Test
    public void shouldSuccessfullyGetServiceTypeByName() throws Exception {
        //GIVEN
        String name = "Мойка";
        //WHEN
        ServiceType serviceType = ServiceType.getTypeByName(name);
        //THEN
        assertThat(serviceType,is(WASHING));
    }

    @Test(expected = ConvertTypeException.class)
    public void shouldThrowExceptionIfCantGetServiceTypeByName() throws Exception {
        //GIVEN
        String name = "Чистка Зубов";
        //WHEN
        ServiceType serviceType = ServiceType.getTypeByName(name);
    }
}