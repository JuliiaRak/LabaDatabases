package solvd.laba.itcompany.service.impl;

import solvd.laba.itcompany.domain.Department;
import solvd.laba.itcompany.persistence.DepartmentRepository;
import solvd.laba.itcompany.persistence.impl.DepartmentJdbsRepository;
import solvd.laba.itcompany.service.DepartmentService;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl() {
        departmentRepository = new DepartmentJdbsRepository();
    }

    @Override
    public void create(Department department) {
        departmentRepository.create(department);
    }

    @Override
    public void update(Department department) {
        departmentRepository.update(department);
    }

    @Override
    public Department findById(Long departmentId) {
        return departmentRepository.findById(departmentId);
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public void deleteById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }
}
