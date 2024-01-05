package solvd.laba.itcompany.persistence.impl;

import solvd.laba.itcompany.domain.Project;
import solvd.laba.itcompany.domain.Service;
import solvd.laba.itcompany.domain.Skill;
import solvd.laba.itcompany.domain.exception.PersistenceException;
import solvd.laba.itcompany.persistence.ProjectRepository;
import solvd.laba.itcompany.persistence.config.ConfigurationMyBatis;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ProjectMyBatisRepository implements ProjectRepository{

    @Override
    public void create(Project project) {
        try (SqlSession sqlSession = ConfigurationMyBatis.getSessionFactory().openSession(true)) {
            sqlSession.getMapper(ProjectRepository.class).create(project);
        } catch (Exception e) {
            throw new PersistenceException("Unable to create project", e);
        }
    }

    @Override
    public Project findById(Long projectId) {
        try (SqlSession sqlSession = ConfigurationMyBatis.getSessionFactory().openSession(true)) {
            return sqlSession.getMapper(ProjectRepository.class).findById(projectId);
        } catch (Exception e) {
            throw new PersistenceException("Unable to find project by id", e);
        }
    }

    @Override
    public List<Project> findAll() {
        try (SqlSession sqlSession = ConfigurationMyBatis.getSessionFactory().openSession(true)) {
            return sqlSession.getMapper(ProjectRepository.class).findAll();
        } catch (Exception e) {
            throw new PersistenceException("Unable to find projects", e);
        }
    }

    @Override
    public void deleteById(Long projectId) {
        try (SqlSession sqlSession = ConfigurationMyBatis.getSessionFactory().openSession(true)) {
            sqlSession.getMapper(ProjectRepository.class).deleteById(projectId);
        } catch (Exception e) {
            throw new PersistenceException("Unable to delete project", e);
        }
    }

    @Override
    public void addService(Long projectId, Long serviceId) {
        try (SqlSession sqlSession = ConfigurationMyBatis.getSessionFactory().openSession(true)) {
            sqlSession.getMapper(ProjectRepository.class).addService(Long projectId, Long serviceId);
        } catch (Exception e) {
            throw new PersistenceException("Unable to delete project", e);
        }
    }

    @Override
    public List<Service> findServicesByProjectId(Long projectId) {
        try (SqlSession sqlSession = ConfigurationMyBatis.getSessionFactory().openSession(true)) {
            return sqlSession.getMapper(ProjectRepository.class).findServicesByProjectId(Long projectId);
        } catch (Exception e) {
            throw new PersistenceException("Unable to delete project", e);
        }
    }
}
