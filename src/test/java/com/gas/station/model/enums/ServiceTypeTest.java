package com.gas.station.model.enums;

import com.gas.station.exception.ConvertTypeException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.gas.station.exception.ConvertTypeException.message;
import static com.gas.station.model.enums.ServiceType.WASHING;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ServiceTypeTest {

    @Rule
    public ExpectedException exceptions = ExpectedException.none();

    @Test
    public void shouldSuccessfullyGetServiceTypeByName() throws Exception {
        //GIVEN
        String name = "Мойка";
        //WHEN
        ServiceType serviceType = ServiceType.getTypeByName(name);
        //THEN
        assertThat(serviceType, is(WASHING));
    }

    @Test
    public void shouldThrowExceptionIfCantGetServiceTypeByName() throws Exception {
        //GIVEN
        String name = "Чистка Зубов";
        //THEN
        exceptions.expect(ConvertTypeException.class);
        exceptions.expectMessage(message + name);
        //WHEN
        ServiceType serviceType = ServiceType.getTypeByName(name);
    }
}