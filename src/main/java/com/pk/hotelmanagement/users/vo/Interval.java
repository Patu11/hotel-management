package com.pk.hotelmanagement.users.vo;

import lombok.Getter;

import java.util.Date;

@Getter
public class Interval {
    private final Date startDate;
    private final Date endDate;

    public Interval(Date startDate, Date endDate) {
        if(startDate == null || endDate == null) {
            throw new IllegalArgumentException("Dates are null");
        }
        if (endDate.before(startDate)) {
            throw new IllegalArgumentException("End date is before start date!");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }


}
