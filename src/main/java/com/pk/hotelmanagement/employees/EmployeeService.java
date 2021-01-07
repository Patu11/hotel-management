package com.pk.hotelmanagement.employees;

import com.pk.hotelmanagement.employees.schedule.Schedule;
import com.pk.hotelmanagement.employees.schedule.ScheduleData;
import com.pk.hotelmanagement.employees.schedule.ScheduleDto;
import com.pk.hotelmanagement.hotel.NotFoundException;
import com.pk.hotelmanagement.users.registration.RegistrationData;
import com.pk.hotelmanagement.users.registration.UserRegistrationService;
import com.pk.hotelmanagement.users.registration.persons.Person;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    @Transactional(readOnly = true)
    public List<EmployeeDto> getAllEmployees() {
        return repository.getAllEmployees();
    }

    @Transactional(readOnly = true)
    public EmployeeDto getEmployee(int id) {
        return repository.getEmployeeById(id);
    }

    @Transactional
    public void removeEmployee(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
    }

    @Transactional
    public void addScheduleToEmployee(ScheduleData scheduleData) {
        Employee employee = findEmployee(scheduleData.getEmployeeId());
        employee.addSchedule(createScheduleFromScheduleData(scheduleData));
    }

    @Transactional(readOnly = true)
    public List<ScheduleDto> getAllExistingSchedules() {
        return  repository.getAllSchedules();
    }

    @Transactional(readOnly = true)
    public List<ScheduleDto> getAllCurrentSchedules() {
        return  repository.getAllCurrentSchedules();
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

    private Employee findEmployee(int employeeId) {
        Optional<Employee> employee = repository.findById(employeeId);
        return employee.orElseThrow(() -> new NotFoundException("Employee not found with id: " + employeeId));
    }

    private Schedule createScheduleFromScheduleData(ScheduleData scheduleData) {
        Schedule schedule = new Schedule();
        schedule.setStartDate(scheduleData.getInterval().getStartDate());
        schedule.setEndDate(scheduleData.getInterval().getEndDate());
        return schedule;
    }
}
