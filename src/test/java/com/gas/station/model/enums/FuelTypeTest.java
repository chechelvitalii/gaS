package com.gas.station.model.enums;

import com.gas.station.exception.ResolveTypeException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.gas.station.exception.ResolveTypeException.message;
import static com.gas.station.model.enums.FuelType.A_95;
import static com.gas.station.model.enums.FuelType.getFuelTypeByName;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FuelTypeTest {

    @Rule
    public ExpectedException exceptions = ExpectedException.none();

    @Test
    public void shouldSuccessfullyGetFuelTypeByName() throws Exception {
        //GIVEN
        String name = "A-95";
        //WHEN
        FuelType fuelType = FuelType.getFuelTypeByName(name);
        //THEN
        assertThat(fuelType, is(A_95));
    }

    @Test
    public void shouldThrowExceptionIfCantGetFuelTypeByName() throws Exception {
        //GIVEN
        String name = "A-10050";
        //THEN
        exceptions.expect(ResolveTypeException.class);
        exceptions.expectMessage(message + name);
        //WHEN
        FuelType fuelType = getFuelTypeByName(name);
    }
}