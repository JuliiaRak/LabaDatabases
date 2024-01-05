package solvd.laba.itcompany.persistence.impl;

import org.apache.ibatis.session.SqlSession;
import solvd.laba.itcompany.domain.Skill;
import solvd.laba.itcompany.domain.exception.PersistenceException;
import solvd.laba.itcompany.persistence.SkillRepository;
import solvd.laba.itcompany.persistence.config.ConfigurationMyBatis;

public class SkillMyBatisRepository implements SkillRepository {

    @Override
    public void create(Skill skill) {
        try (SqlSession sqlSession = ConfigurationMyBatis.getSessionFactory().openSession(true)) {
            sqlSession.getMapper(SkillRepository.class).create(skill);
        } catch (Exception e) {
            throw new PersistenceException("Unable to create skill", e);
        }
    }
}
