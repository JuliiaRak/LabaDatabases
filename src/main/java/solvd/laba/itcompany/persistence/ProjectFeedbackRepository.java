package solvd.laba.itcompany.persistence;

import solvd.laba.itcompany.domain.ProjectFeedback;

import java.util.List;

public interface ProjectFeedbackRepository {

    void create(ProjectFeedback projectFeedback);

    ProjectFeedback findById(Long projectFeedbackId);

    List<ProjectFeedback> findAll();


}
