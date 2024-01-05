package solvd.laba.itcompany.persistence.impl;

import solvd.laba.itcompany.domain.Vacation;
import solvd.laba.itcompany.domain.exception.PersistenceException;
import solvd.laba.itcompany.persistence.VacationRepository;
import solvd.laba.itcompany.persistence.config.ConfigurationMyBatis;
import org.apache.ibatis.session.SqlSession;

public class VacationMyBatisRepository implements VacationRepository {

    @Override
    public void create(Vacation vacation) {
        try (SqlSession sqlSession = ConfigurationMyBatis.getSessionFactory().openSession(true)) {
            sqlSession.getMapper(VacationRepository.class).create(vacation);
        } catch (Exception e) {
            throw new PersistenceException("Unable to create vacation", e);
        }
    }
}
