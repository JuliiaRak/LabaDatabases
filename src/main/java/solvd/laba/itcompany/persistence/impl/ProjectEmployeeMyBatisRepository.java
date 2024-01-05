package solvd.laba.itcompany.persistence.impl;

import solvd.laba.itcompany.domain.ProjectEmployee;
import solvd.laba.itcompany.domain.exception.PersistenceException;
import solvd.laba.itcompany.persistence.ProjectEmployeeRepository;
import solvd.laba.itcompany.persistence.config.ConfigurationMyBatis;
import org.apache.ibatis.session.SqlSession;

public class ProjectEmployeeMyBatisRepository implements ProjectEmployeeRepository {

    @Override
    public void create(ProjectEmployee projectEmployee) {
        try (SqlSession sqlSession = ConfigurationMyBatis.getSessionFactory().openSession(true)) {
            sqlSession.getMapper(ProjectEmployeeRepository.class).create(projectEmployee);
        } catch (Exception e) {
            throw new PersistenceException("Unable to create project employee", e);
        }
    }
}
