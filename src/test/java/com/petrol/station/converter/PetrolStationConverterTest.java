
package com.petrol.station.converter;

import com.petrol.station.dto.PetrolStationDto;
import com.petrol.station.model.Address;
import com.petrol.station.model.PetrolStation;
import com.petrol.station.model.enums.BrandType;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class PetrolStationConverterTest {
    private static final BrandType BRAND = BrandType.WOG;
    private static final String LAT = "10";
    private static final String LNG = "20";
    private static final String CITY = "Dnepr";
    private static final String STREET = "12 kv";

    PetrolStationConverter converter = new PetrolStationConverter();

    @Test
    public void shouldSuccessfullyConvertPetrolStation() throws Exception {
        //GIVEN
        PetrolStation petrolStation = createPetrolStation();
        //WHEN
        PetrolStationDto actual = converter.convert(petrolStation);
        //THEN
        assertThat(actual.getBrand(), Matchers.is(BRAND.name()));
        assertThat(actual.getCity(), Matchers.is(CITY));
        assertThat(actual.getStreet(), Matchers.is(STREET));
        assertThat(actual.getLat(), Matchers.is(LAT));
        assertThat(actual.getLng(), Matchers.is(LNG));
    }

    private PetrolStation createPetrolStation() {
        PetrolStation petrolStation = new PetrolStation();
        petrolStation.setBrandType(BRAND);
        Address address = new Address();
        address.setLat(LAT);
        address.setLng(LNG);
        address.setCity(CITY);
        address.setStreet(STREET);
        petrolStation.setAddress(address);
        return petrolStation;
    }

}