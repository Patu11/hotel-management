package com.pk.hotelmanagement.employees.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Salary implements Serializable {
    @Column
    private final double salary;

    private Salary() {
        this.salary = 0.0;
    }

    public Salary(String salary) {
        this(Double.parseDouble(salary));
    }

    public Salary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary can not be less than 0");
        }
        this.salary = salary;
    }

    public double getAsDouble() {
        return this.salary;
    }
}
