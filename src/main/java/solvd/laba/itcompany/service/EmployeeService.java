package solvd.laba.itcompany.service;

import solvd.laba.itcompany.domain.Employee;
import solvd.laba.itcompany.domain.Skill;

import java.util.List;

public interface EmployeeService {

    void create(Employee employee);

    Employee findById(Long employeeId);

    List<Employee> findAll();

    void addSkill(Long employeeId, Long skilId);

    List<Skill> findSkillsByEmployeeId (Long employeeId);


}
