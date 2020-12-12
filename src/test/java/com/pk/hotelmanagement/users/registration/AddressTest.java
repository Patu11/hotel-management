package com.pk.hotelmanagement.users.registration;

import com.pk.hotelmanagement.users.vo.Address;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AddressTest extends Assertions {

    @ParameterizedTest
    @CsvSource({
            "17,kamienna,Krakow,54-432",
            "13,Szkolna,Warszawa,13-784"
    })
    public void shouldCreateAddressInstance(String houseNr, String street, String city, String postalCode) {
        //given
        String address = houseNr + ";" + street + ";" + city + ";" + postalCode;
        //when
        Address adrs = new Address(address);
        //then
        assertThat(adrs).extracting("houseNr", "street", "town", "postCode.postCode").containsExactly(
                houseNr,street,city,postalCode
        );
    }

    @ParameterizedTest
    @CsvSource({
            "ab,kamienna,Krakow,54-432",
            "13,'',Warszawa,13-784",
            "13,Szkolna,'',13-784",
            "13,Szkolna,Warszawa,7b-7c4"
    })
    public void shouldThrowExceptionWhenGivenAddressIsWrong(String houseNr, String street, String city, String postalCode) {
        //given
        String address = houseNr + ";" + street + ";" + city + ";" + postalCode;
        //when
        assertThatThrownBy(() -> new Address(address)).isInstanceOf(IllegalArgumentException.class);
    }

}