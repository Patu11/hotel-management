package com.pk.hotelmanagement.employees;

import com.pk.hotelmanagement.employees.schedule.ScheduleDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("SELECT new com.pk.hotelmanagement.employees.EmployeeDto(p.personId, u.email.email, " +
            "p.name, p.surname, p.phoneNr, p.birthdate, p.address.address, e.position, " +
            "e.salary.salary) FROM Employee e JOIN e.person p JOIN p.user u")
    List<EmployeeDto> getAllEmployees();

    @Query("SELECT new com.pk.hotelmanagement.employees.EmployeeDto(p.personId, u.email.email, " +
            "p.name, p.surname, p.phoneNr, p.birthdate, p.address.address, e.position, " +
            "e.salary.salary) FROM Employee e JOIN e.person p JOIN p.user u WHERE p.personId = :id")
    EmployeeDto getEmployeeById(int id);

    @Query("SELECT new com.pk.hotelmanagement.employees.schedule.ScheduleDto(s.id, e.id, s.startDate, s.endDate) FROM Schedule s JOIN s.employee e")
    List<ScheduleDto> getAllSchedules();

    @Query("SELECT new com.pk.hotelmanagement.employees.schedule.ScheduleDto(s.id, e.id, s.startDate, s.endDate) FROM Schedule s JOIN s.employee e WHERE s.startDate >= CURRENT_TIMESTAMP ")
    List<ScheduleDto> getAllCurrentSchedules();
}
