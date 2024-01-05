package solvd.laba.itcompany.persistence.impl;

import solvd.laba.itcompany.domain.Task;
import solvd.laba.itcompany.domain.exception.PersistenceException;
import solvd.laba.itcompany.persistence.TaskRepository;
import solvd.laba.itcompany.persistence.config.ConfigurationMyBatis;
import org.apache.ibatis.session.SqlSession;

public class TaskMyBatisRepository implements TaskRepository {

    @Override
    public void create(Task task) {
        try (SqlSession sqlSession = ConfigurationMyBatis.getSessionFactory().openSession(true)) {
            sqlSession.getMapper(TaskRepository.class).create(task);
        } catch (Exception e) {
            throw new PersistenceException("Unable to create task", e);
        }
    }
}
