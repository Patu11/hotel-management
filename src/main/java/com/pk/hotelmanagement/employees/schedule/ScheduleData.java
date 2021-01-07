package com.pk.hotelmanagement.employees.schedule;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ScheduleData {
    @NotNull
    private int employeeId;
    private ScheduleInterval interval;
}
