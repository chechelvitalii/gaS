package com.gas.station.model.enums;

import com.gas.station.exception.ConvertTypeException;
import org.junit.Test;

import static com.gas.station.model.enums.FuelType.A_95;
import static com.gas.station.model.enums.FuelType.getFuelTypeByName;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class FuelTypeTest {
    @Test
    public void shouldSuccessfullyGetFuelTypeByName() throws Exception {
        //GIVEN
        String name = "A-95";
        //WHEN
        FuelType fuelType = FuelType.getFuelTypeByName(name);
        //THEN
        assertThat(fuelType,is(A_95));
    }

    @Test(expected = ConvertTypeException.class)
    public void shouldThrowExceptionIfCantGetFuelTypeByName() throws Exception {
        //GIVEN
        String name = "A-10050";
        //WHEN
        FuelType fuelType = getFuelTypeByName(name);
    }
}