package com.pk.hotelmanagement.employees;

import com.pk.hotelmanagement.employees.schedule.Schedule;
import com.pk.hotelmanagement.employees.vo.Salary;
import com.pk.hotelmanagement.users.registration.persons.Person;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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
    @JoinColumn(name = "id", referencedColumnName = "person_id")
    private Person person;

    @OneToMany(mappedBy = "employee", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Schedule> scheduleList;

    public void addSchedule(Schedule schedule) {
        scheduleList.add(schedule);
        schedule.setEmployee(this);
    }

    public void removeSchedule(Schedule schedule) {
        scheduleList.remove(schedule);
        schedule.setEmployee(null);
    }
}
