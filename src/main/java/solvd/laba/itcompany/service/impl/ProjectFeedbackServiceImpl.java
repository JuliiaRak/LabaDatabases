package solvd.laba.itcompany.service.impl;

import solvd.laba.itcompany.domain.ProjectFeedback;
import solvd.laba.itcompany.domain.exception.ServiceException;
import solvd.laba.itcompany.persistence.ProjectFeedbackRepository;
import solvd.laba.itcompany.persistence.impl.ProjectFeedbackJdbsRepository;
import solvd.laba.itcompany.persistence.impl.ProjectFeedbackMyBatisRepository;
import solvd.laba.itcompany.service.ProjectFeedbackService;

import java.util.List;

public class ProjectFeedbackServiceImpl implements ProjectFeedbackService {
    private final ProjectFeedbackRepository projectFeedbackRepository;

    public ProjectFeedbackServiceImpl() {
        projectFeedbackRepository = new ProjectFeedbackMyBatisRepository();
    }

    @Override
    public void create(ProjectFeedback projectFeedback) {
        try {
            projectFeedbackRepository.create(projectFeedback);
        } catch (Exception e) {
            throw new ServiceException("Failed to create project feedback", e);
        }
    }

    @Override
    public List<ProjectFeedback> findAll() {
        try {
            return projectFeedbackRepository.findAll();
        } catch (Exception e) {
            throw new ServiceException("Failed to find project feedbacks", e);
        }
    }
}
