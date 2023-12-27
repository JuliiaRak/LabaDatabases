package solvd.laba.itcompany.persistence.impl;

import solvd.laba.itcompany.domain.Employee;
import solvd.laba.itcompany.persistence.EmployeeRepository;

import java.util.List;

public class EmployeeJdbsRepository implements EmployeeRepository {
    @Override
    public void create(Employee employee) {

    }

    @Override
    public Employee findById(Long employeeId) {
        return null;
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }
}
