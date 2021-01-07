package com.pk.hotelmanagement.admin.controllers;

import com.pk.hotelmanagement.employees.EmployeeService;
import com.pk.hotelmanagement.employees.schedule.ScheduleData;
import com.pk.hotelmanagement.employees.schedule.ScheduleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/employees")
public class SchedulesManagementController {
    private final EmployeeService employeeService;

    @Autowired
    public SchedulesManagementController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/schedules")
    public ResponseEntity<?> createScheduleForGivenEmployee(@RequestBody ScheduleData scheduleData) {
        employeeService.addScheduleToEmployee(scheduleData);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/schedules")
    public ResponseEntity<?> getExistingSchedules() {
        List<ScheduleDto> allSchedules = employeeService.getAllExistingSchedules();
        return ResponseEntity.ok(allSchedules);
    }

    @GetMapping("/schedules/current")
    public ResponseEntity<?> getAllCurrentSchedules() {
        List<ScheduleDto> currentSchedules = employeeService.getAllCurrentSchedules();
        return ResponseEntity.ok(currentSchedules);
    }

    @DeleteMapping("/schedules/{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable int id) {
        employeeService.deleteSchedule(id);
        return ResponseEntity.ok().build();
    }
}
