package solvd.laba.itcompany.persistence;

import solvd.laba.itcompany.domain.Employee;
import solvd.laba.itcompany.domain.Skill;

import java.util.List;

public interface EmployeeRepository {

    void create(Employee employee);

    Employee findById(Long employeeId);

    List<Employee> findAll();

    void addSkill(Long employeeId, Long skilId);

    List<Skill> findSkillsByEmployeeId (Long employeeId);

}
