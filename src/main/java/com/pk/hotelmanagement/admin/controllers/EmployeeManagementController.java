package com.pk.hotelmanagement.admin.controllers;

import com.pk.hotelmanagement.employees.EmployeeRegistrationData;
import com.pk.hotelmanagement.employees.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
