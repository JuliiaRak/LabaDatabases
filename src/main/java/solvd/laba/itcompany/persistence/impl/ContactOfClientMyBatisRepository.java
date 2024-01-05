package solvd.laba.itcompany.persistence.impl;

import solvd.laba.itcompany.domain.ContactOfClient;
import solvd.laba.itcompany.domain.exception.PersistenceException;
import solvd.laba.itcompany.persistence.ContactOfClientRepository;
import solvd.laba.itcompany.persistence.config.ConfigurationMyBatis;
import org.apache.ibatis.session.SqlSession;

public class ContactOfClientMyBatisRepository implements ContactOfClientRepository {

    @Override
    public void create(ContactOfClient contactOfClient) {
        try (SqlSession sqlSession = ConfigurationMyBatis.getSessionFactory().openSession(true)) {
            sqlSession.getMapper(ContactOfClientRepository.class).create(contactOfClient);
        } catch (Exception e) {
            throw new PersistenceException("Unable to create contact of client", e);
        }
    }
}
