package com.pk.hotelmanagement.employees.controllers;

import com.pk.hotelmanagement.employees.EmployeeService;
import com.pk.hotelmanagement.employees.schedule.ScheduleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees/schedules")
public class EmployeeSchedulesController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeSchedulesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<?> getAllCurrentSchedules() {
        List<ScheduleDto> schedules = employeeService.getAllCurrentSchedulesForEmployee();
        return ResponseEntity.ok(schedules);
    }


}
