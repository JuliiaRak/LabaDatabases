package solvd.laba.itcompany.persistence.impl;

import org.apache.ibatis.session.SqlSession;
import solvd.laba.itcompany.domain.Certification;
import solvd.laba.itcompany.domain.exception.PersistenceException;
import solvd.laba.itcompany.persistence.CertificationRepository;
import solvd.laba.itcompany.persistence.config.ConfigurationMyBatis;

import java.util.List;

public class CertificationMyBatisRepository implements CertificationRepository {

    @Override
    public void create(Certification certification) {
        try (SqlSession sqlSession = ConfigurationMyBatis.getSessionFactory().openSession(true)) {
            sqlSession.getMapper(CertificationRepository.class).create(certification);
        } catch (Exception e) {
            throw new PersistenceException("Unable to create certification", e);
        }
    }

    @Override
    public List<Certification> findAll() {
        try (SqlSession sqlSession = ConfigurationMyBatis.getSessionFactory().openSession(true)) {
            return sqlSession.getMapper(CertificationRepository.class).findAll();
        } catch (Exception e) {
            throw new PersistenceException("Unable to find all certifications", e);
        }
    }
}
