package solvd.laba.itcompany.persistence;

import solvd.laba.itcompany.domain.Employee;

import java.util.List;

public interface EmployeeRepository {

    void create(Employee employee);

    Employee findById(Long employeeId);

    List<Employee> findAll();

}
