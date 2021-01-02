package com.pk.hotelmanagement.employees;

import com.pk.hotelmanagement.employees.vo.Salary;
import com.pk.hotelmanagement.users.registration.persons.Person;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    private int id;

    @Column
    @Enumerated(EnumType.STRING)
    private Position position;

    @Embedded
    private Salary salary;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @MapsId
    @JoinColumn(name = "id", referencedColumnName = "personId")
    private Person person;
}
