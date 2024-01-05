package solvd.laba.itcompany.persistence.impl;

import solvd.laba.itcompany.domain.ProjectFeedback;
import solvd.laba.itcompany.domain.exception.PersistenceException;
import solvd.laba.itcompany.persistence.ProjectFeedbackRepository;
import solvd.laba.itcompany.persistence.config.ConfigurationMyBatis;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ProjectFeedbackMyBatisRepository implements ProjectFeedbackRepository {

    @Override
    public void create(ProjectFeedback projectFeedback) {
        try (SqlSession sqlSession = ConfigurationMyBatis.getSessionFactory().openSession(true)) {
            sqlSession.getMapper(ProjectFeedbackRepository.class).create(projectFeedback);
        } catch (Exception e) {
            throw new PersistenceException("Unable to create project feedback", e);
        }
    }

    @Override
    public List<ProjectFeedback> findAll() {
        try (SqlSession sqlSession = ConfigurationMyBatis.getSessionFactory().openSession(true)) {
            return sqlSession.getMapper(ProjectFeedbackRepository.class).findAll();
        } catch (Exception e) {
            throw new PersistenceException("Unable to find project feedbacks", e);
        }
    }
}
