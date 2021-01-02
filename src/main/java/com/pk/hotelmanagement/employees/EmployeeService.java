package com.pk.hotelmanagement.employees;

import com.pk.hotelmanagement.users.registration.RegistrationData;
import com.pk.hotelmanagement.users.registration.UserRegistrationService;
import com.pk.hotelmanagement.users.registration.persons.Person;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;
    private final UserRegistrationService userRegistrationService;

    @Autowired
    public EmployeeService(EmployeeRepository repository, UserRegistrationService userRegistrationService) {
        this.repository = repository;
        this.userRegistrationService = userRegistrationService;
    }

    @Transactional
    public void addEmployee(EmployeeRegistrationData employeeRegistrationData) {
        RegistrationData registrationData = new RegistrationData();
        BeanUtils.copyProperties(employeeRegistrationData, registrationData);
        Person person = createPerson(employeeRegistrationData);
        userRegistrationService.registerEmployee(registrationData, person);
        Employee employee = createEmployee(employeeRegistrationData);
        employee.setPerson(userRegistrationService.getUserByEmail(employeeRegistrationData.getEmail()).getPerson()); // to get updated value of person id
        repository.save(employee);
    }

    private Employee createEmployee(EmployeeRegistrationData registrationData) {
        Employee employee = new Employee();
        employee.setPosition(registrationData.getPosition());
        employee.setSalary(registrationData.getSalary());
        return employee;
    }

    private Person createPerson(EmployeeRegistrationData registrationData) {
        Person person = new Person();
        BeanUtils.copyProperties(registrationData, person);
        return person;
    }
}
