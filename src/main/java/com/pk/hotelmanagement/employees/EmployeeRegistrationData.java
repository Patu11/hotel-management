package com.pk.hotelmanagement.employees;

import com.pk.hotelmanagement.employees.vo.Salary;
import com.pk.hotelmanagement.users.registration.RegistrationData;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
public class EmployeeRegistrationData extends RegistrationData {

    @NotNull
    private Position position;

    @NotNull
    private Salary salary;
}
