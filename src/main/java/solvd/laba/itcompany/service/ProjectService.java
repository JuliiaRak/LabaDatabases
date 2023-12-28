package solvd.laba.itcompany.service;

import solvd.laba.itcompany.domain.Project;

import java.util.List;

public interface ProjectService {

    void create(Project project);

    Project findById(Long projectId);

    List<Project> findAll();

    void deleteById(Long projectId);

}
