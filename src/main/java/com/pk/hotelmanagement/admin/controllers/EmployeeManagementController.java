package com.pk.hotelmanagement.admin.controllers;

import com.pk.hotelmanagement.employees.EmployeeDto;
import com.pk.hotelmanagement.employees.EmployeeRegistrationData;
import com.pk.hotelmanagement.employees.EmployeeService;
import com.pk.hotelmanagement.employees.schedule.ScheduleData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class EmployeeManagementController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeManagementController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employees")
    public ResponseEntity<?> addEmployee(@Valid @RequestBody EmployeeRegistrationData registrationData) {
        employeeService.addEmployee(registrationData);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/employees")
    public ResponseEntity<?> getAllEmployees() {
        List<EmployeeDto> allEmployees = employeeService.getAllEmployees();
        return ResponseEntity.ok(allEmployees);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable int id) {
        EmployeeDto employee = employeeService.getEmployee(id);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> removeEmployee(@PathVariable int id) {
        employeeService.removeEmployee(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/employees/schedules")
    public ResponseEntity<?> createScheduleForGivenEmployee(@RequestBody ScheduleData scheduleData) {
        employeeService.addScheduleToEmployee(scheduleData);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
