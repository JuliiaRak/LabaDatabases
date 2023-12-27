package solvd.laba.itcompany.persistence.impl;

import solvd.laba.itcompany.domain.Project;
import solvd.laba.itcompany.persistence.ProjectRepository;

import java.util.List;

public class ProjectJdbsRepository implements ProjectRepository {
    @Override
    public void create(Project project) {

    }

    @Override
    public Project findById(Long projectId) {
        return null;
    }

    @Override
    public List<Project> findAll() {
        return null;
    }

    @Override
    public void deleteById(Long projectId) {

    }
}
