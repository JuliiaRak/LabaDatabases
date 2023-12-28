package solvd.laba.itcompany.service.impl;

import solvd.laba.itcompany.domain.Employee;
import solvd.laba.itcompany.persistence.DepartmentRepository;
import solvd.laba.itcompany.persistence.EmployeeRepository;
import solvd.laba.itcompany.persistence.impl.EmployeeJdbsRepository;
import solvd.laba.itcompany.service.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public  EmployeeServiceImpl(){
        employeeRepository = new EmployeeJdbsRepository();
    }

    @Override
    public void create(Employee employee) {
        employeeRepository.create(employee);
    }

    @Override
    public Employee findById(Long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
}
