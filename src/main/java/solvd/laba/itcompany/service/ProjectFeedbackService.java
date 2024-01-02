package solvd.laba.itcompany.service;

import solvd.laba.itcompany.domain.ProjectFeedback;

import java.util.List;

public interface ProjectFeedbackService {

    void create(ProjectFeedback projectFeedback);

    List<ProjectFeedback> findAll();

}
