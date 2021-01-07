package com.pk.hotelmanagement.employees.schedule;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pk.hotelmanagement.users.vo.Interval;

import java.util.Date;

public class ScheduleInterval extends Interval {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private final Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
    private final Date endDate;
    public ScheduleInterval(Date startDate, Date endDate) {
        super(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    @Override
    public Date getEndDate() {
        return endDate;
    }
}
