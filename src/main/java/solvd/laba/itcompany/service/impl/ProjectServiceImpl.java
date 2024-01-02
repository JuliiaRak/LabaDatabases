package solvd.laba.itcompany.service.impl;

import solvd.laba.itcompany.domain.Project;
import solvd.laba.itcompany.domain.exception.ServiceException;
import solvd.laba.itcompany.persistence.ProjectRepository;
import solvd.laba.itcompany.persistence.impl.ProjectJdbsRepository;
import solvd.laba.itcompany.service.EmployeeService;
import solvd.laba.itcompany.service.ProjectService;
import solvd.laba.itcompany.service.ServiceService;

import java.util.List;

public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final EmployeeService employeeService;
    private final ServiceService serviceService;

    public ProjectServiceImpl() {
        projectRepository = new ProjectJdbsRepository();
        employeeService = new EmployeeServiceImpl();
        serviceService = new ServiceServiceImpl();
    }

    @Override
    public void create(Project project) {
        try {
            projectRepository.create(project);

            if (project.getEmployees() != null) {
                project.getEmployees().stream().forEach(employee -> employeeService.create(employee));
            }
            if (project.getServices() != null) {
                project.getServices().stream().forEach(service -> serviceService.create(service));
            }
        } catch (Exception e) {
            throw new ServiceException("Failed to create project", e);
        }
    }

    @Override
    public Project findById(Long projectId) {
        try {
            return projectRepository.findById(projectId);
        } catch (Exception e) {
            throw new ServiceException("Failed to find project by id", e);
        }
    }

    @Override
    public List<Project> findAll() {
        try {
            return projectRepository.findAll();
        } catch (Exception e) {
            throw new ServiceException("Failed to find projects", e);
        }
    }

    @Override
    public void deleteById(Long projectId) {
        try {
            projectRepository.deleteById(projectId);
        } catch (Exception e) {
            throw new ServiceException("Failed to delete project", e);
        }
    }
}
