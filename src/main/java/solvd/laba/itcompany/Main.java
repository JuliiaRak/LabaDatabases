package solvd.laba.itcompany;

import solvd.laba.itcompany.domain.Department;
import solvd.laba.itcompany.domain.Employee;
import solvd.laba.itcompany.domain.Skill;
import solvd.laba.itcompany.persistence.DepartmentRepository;
import solvd.laba.itcompany.persistence.EmployeeRepository;
import solvd.laba.itcompany.persistence.SkillRepository;
import solvd.laba.itcompany.persistence.impl.DepartmentJdbsRepository;
import solvd.laba.itcompany.persistence.impl.EmployeeJdbsRepository;
import solvd.laba.itcompany.persistence.impl.SkillJdbsRepository;
import solvd.laba.itcompany.service.DepartmentService;
import solvd.laba.itcompany.service.EmployeeService;
import solvd.laba.itcompany.service.SkillService;
import solvd.laba.itcompany.service.impl.DepartmentServiceImpl;
import solvd.laba.itcompany.service.impl.EmployeeServiceImpl;
import solvd.laba.itcompany.service.impl.SkillServiceImpl;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        //department
        DepartmentService departmentService = new DepartmentServiceImpl();
        Department department = new Department();
        department.setDepartmentName("GameDev");
        department.setId(6L);
//        departmentService.create(department);
//        System.out.println(departmentService.findAll());
//        System.out.println(departmentService.findById(department.getId()));

        //skills
        SkillService skillService = new SkillServiceImpl();
        Skill skill = new Skill();
        skill.setSkillName("Game Developing");
        skillService.create(skill);


        //employees
        EmployeeService employeeService = new EmployeeServiceImpl();
        Employee employee = new Employee(null, department, "Julia", "Rak", "Game Developer", new BigDecimal(30000), null);
        employeeService.create(employee);
        System.out.println(employeeService.findAll());
        System.out.println(employeeService.findById(employee.getId()));
    }
}
