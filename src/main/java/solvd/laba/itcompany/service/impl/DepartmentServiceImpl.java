package solvd.laba.itcompany.service.impl;

import solvd.laba.itcompany.domain.Department;
import solvd.laba.itcompany.domain.exception.ServiceException;
import solvd.laba.itcompany.persistence.DepartmentRepository;
import solvd.laba.itcompany.persistence.impl.DepartmentJdbsRepository;
import solvd.laba.itcompany.service.DepartmentService;

import javax.sql.rowset.serial.SerialException;
import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl() {
        departmentRepository = new DepartmentJdbsRepository();
    }

    @Override
    public void create(Department department) {
        try {
            departmentRepository.create(department);
        } catch (Exception e) {
            throw new ServiceException("Failed to create department", e);
        }
    }

    @Override
    public void update(Department department) {
        try {
            departmentRepository.update(department);
        } catch (Exception e) {
            throw new ServiceException("Failed to update department", e);
        }
    }

    @Override
    public Department findById(Long departmentId) {
        try {
            return departmentRepository.findById(departmentId);
        } catch (Exception e) {
            throw new ServiceException("Failed to find department with id" + departmentId, e);
        }
    }

    @Override
    public List<Department> findAll() {
        try {
            return departmentRepository.findAll();
        } catch (Exception e) {
            throw new ServiceException("Failed to find all departments", e);
        }
    }

    @Override
    public void deleteById(Long departmentId) {
        try {
            departmentRepository.deleteById(departmentId);
        } catch (Exception e) {
            throw new ServiceException("Failed to delete department with id" + departmentId, e);
        }
    }
}
