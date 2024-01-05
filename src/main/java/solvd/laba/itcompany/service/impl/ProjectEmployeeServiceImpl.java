package solvd.laba.itcompany.service.impl;

import solvd.laba.itcompany.domain.ProjectEmployee;
import solvd.laba.itcompany.domain.exception.ServiceException;
import solvd.laba.itcompany.persistence.ProjectEmployeeRepository;
import solvd.laba.itcompany.persistence.impl.ProjectEmployeeJdbsRepository;
import solvd.laba.itcompany.persistence.impl.ProjectEmployeeMyBatisRepository;
import solvd.laba.itcompany.service.ProjectEmployeeService;
import solvd.laba.itcompany.service.ProjectService;

public class ProjectEmployeeServiceImpl implements ProjectEmployeeService {
    private final ProjectEmployeeRepository projectEmployeeRepository;

    public ProjectEmployeeServiceImpl() {
        projectEmployeeRepository = new ProjectEmployeeMyBatisRepository();
    }

    @Override
    public void create(ProjectEmployee projectEmployee) {
        try {
            projectEmployeeRepository.create(projectEmployee);
        } catch (Exception e) {
            throw new ServiceException("Failed to create project employee", e);
        }
    }
}
