package com.pk.hotelmanagement.users.registration;

import com.pk.hotelmanagement.users.vo.PostCode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PostCodeTest extends Assertions {

    @ParameterizedTest
    @ValueSource(strings = {"05-850", "72-010", "16-300", "00-001"})
    public void shouldCreatePostCodeIfStringHasProperStructure(String code) {
        //when
        PostCode postCode = new PostCode(code);
        //then
        assertThat(postCode).extracting("postCode").isEqualTo(code);
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "12345", "8432","5a-7g1","ab-cda"})
    public void shouldThrowExceptionWhenGivenStringHasWrongStructure(String code) {
        assertThatThrownBy(() -> new PostCode(code)).isInstanceOf(IllegalArgumentException.class).hasMessage("Given string is not a post code");

    }

}