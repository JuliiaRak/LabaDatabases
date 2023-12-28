package solvd.laba.itcompany.service;

import solvd.laba.itcompany.domain.Employee;

import java.util.List;

public interface EmployeeService {

    void create(Employee employee);

    Employee findById(Long employeeId);

    List<Employee> findAll();

}
