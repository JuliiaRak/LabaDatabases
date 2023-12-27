package solvd.laba.itcompany.persistence;

import solvd.laba.itcompany.domain.Department;
import java.util.List;

public interface DepartmentRepository {

    void create(Department department);

    void update(Department department);

    Department findById(Long departmentId);

    List<Department> findAll();

    void deleteById(Long departmentId);

}
