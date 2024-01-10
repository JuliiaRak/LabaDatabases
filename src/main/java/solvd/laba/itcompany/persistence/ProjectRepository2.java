package solvd.laba.itcompany.persistence;

import solvd.laba.itcompany.domain.Project;
import solvd.laba.itcompany.domain.Service;

import java.util.List;

public interface ProjectRepository2 {

    void create(Project project);

    Project findById(Long projectId);

    List<Project> findAll();

    void deleteById(Long projectId);

    void addService(Long projectId, Long serviceId);

    List<Service> findServicesByProjectId (Long projectId);

}
