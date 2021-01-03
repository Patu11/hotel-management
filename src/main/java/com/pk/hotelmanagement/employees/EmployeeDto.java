package com.pk.hotelmanagement.employees;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeDto {
    private int id;
    private String email;
    private String name;
    private String surname;
    private int phoneNr;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    private String address;
    private Position position;
    private double salary;
}
