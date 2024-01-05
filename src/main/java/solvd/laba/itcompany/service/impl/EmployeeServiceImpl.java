package solvd.laba.itcompany.service.impl;

import solvd.laba.itcompany.domain.Employee;
import solvd.laba.itcompany.domain.Skill;
import solvd.laba.itcompany.domain.exception.ServiceException;
import solvd.laba.itcompany.persistence.EmployeeRepository;
import solvd.laba.itcompany.persistence.impl.EmployeeJdbsRepository;
import solvd.laba.itcompany.persistence.impl.EmployeeMyBatisRepository;
import solvd.laba.itcompany.service.EmployeeService;
import solvd.laba.itcompany.service.SkillService;

import javax.swing.plaf.SliderUI;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final SkillService skillService;

    public  EmployeeServiceImpl(){
        employeeRepository = new EmployeeMyBatisRepository();
        skillService = new SkillServiceImpl();
    }

    @Override
    public void create(Employee employee) {
        try {
            employeeRepository.create(employee);

            if (employee.getSkills() != null) {
                employee.getSkills().stream().forEach(skill -> skillService.create(skill));
            }
        } catch (Exception e) {
            throw new ServiceException("Failed to create employee", e);
        }
    }

    @Override
    public Employee findById(Long employeeId) {
        try {
            return employeeRepository.findById(employeeId);
        } catch (Exception e) {
            throw new ServiceException("Failed to find employee by id" + employeeId, e);
        }
    }

    @Override
    public List<Employee> findAll() {
        try {
            return employeeRepository.findAll();
        } catch (Exception e) {
            throw new ServiceException("Failed to find all employees", e);
        }
    }

    @Override
    public void addSkill(Long employeeId, Long skilId) {
        try {
            employeeRepository.addSkill(employeeId, skilId);
        } catch (Exception e) {
            throw new ServiceException("Failed to add skill to employee", e);
        }
    }

    @Override
    public List<Skill> findSkillsByEmployeeId(Long employeeId) {
        try {
            return employeeRepository.findSkillsByEmployeeId(employeeId);
        } catch (Exception e) {
            throw new ServiceException("Failed to find all skills of employee", e);
        }
    }
}
