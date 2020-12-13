package com.pk.hotelmanagement.users.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class IntervalTest extends Assertions {

    @Test
    public void shouldThrowExceptionWhenEndDateIsBeforeStartDate() throws ParseException {
        //given
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = simpleDateFormat.parse("2020-6-20");
        Date endDate = simpleDateFormat.parse("2020-5-20");
        //
        assertThatThrownBy(() -> new Interval(startDate, endDate)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("End date is before start date!");

    }

    @Test
    public void shouldThrowExceptionWhenDatesAreNull() {
        assertThatThrownBy(() -> new Interval(null, null)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Dates are null");
    }

}