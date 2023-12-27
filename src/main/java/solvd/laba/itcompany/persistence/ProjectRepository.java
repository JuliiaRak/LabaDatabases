package solvd.laba.itcompany.persistence;

import solvd.laba.itcompany.domain.Project;

import java.util.List;

public interface ProjectRepository {

    void create(Project project);

    Project findById(Long projectId);

    List<Project> findAll();

    void deleteById(Long projectId);

}
