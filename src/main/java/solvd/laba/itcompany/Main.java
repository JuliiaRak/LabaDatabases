package solvd.laba.itcompany;

import solvd.laba.itcompany.domain.Department;
import solvd.laba.itcompany.persistence.DepartmentRepository;
import solvd.laba.itcompany.persistence.impl.DepartmentJdbsRepository;

public class Main {
    public static void main(String[] args) {
        Department department = new Department();
        department.setDepartmentName("AutomationTesting2");
        department.setId(4L);

        DepartmentRepository departmentRepository = new DepartmentJdbsRepository();
        departmentRepository.findById(department.getId());
        departmentRepository.deleteById(department.getId());
        System.out.println(departmentRepository.findAll());


        Department department2 = new Department();
        department2.setDepartmentName("AutomationTesting2");
        departmentRepository.create(department2);

        System.out.println(departmentRepository.findAll());
    }
}
