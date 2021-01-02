package com.pk.hotelmanagement.employees.vo;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Salary implements Serializable {
    private final double salary;

    private Salary() {
        this.salary = 0.0;
    }

    public Salary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary can not be less than 0");
        }
        this.salary = salary;
    }
}
