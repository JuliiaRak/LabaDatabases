package solvd.laba.itcompany.service.impl;

import solvd.laba.itcompany.domain.Project;
import solvd.laba.itcompany.domain.Service;
import solvd.laba.itcompany.domain.exception.ServiceException;
import solvd.laba.itcompany.persistence.impl.ProjectMyBatisRepository;
import solvd.laba.itcompany.service.ProjectService;
import solvd.laba.itcompany.service.ServiceService;

import java.util.List;

public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ServiceService serviceService;

    public ProjectServiceImpl() {
        projectRepository = new ProjectMyBatisRepository();
        serviceService = new ServiceServiceImpl();
    }

    @Override
    public void create(Project project) {
        try {
            projectRepository.create(project);
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

    @Override
    public void addService(Long projectId, Long serviceId) {
        try {
            projectRepository.addService(projectId, serviceId);
        } catch (Exception e) {
            throw new ServiceException("Failed to create project", e);
        }
    }

    @Override
    public List<Service> findServicesByProjectId(Long projectId) {
        try {
            return projectRepository.findServicesByProjectId(projectId);
        } catch (Exception e) {
            throw new ServiceException("Failed to find projects", e);
        }
    }
}
