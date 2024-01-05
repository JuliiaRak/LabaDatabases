package solvd.laba.itcompany.persistence.impl;

import solvd.laba.itcompany.domain.Service;
import solvd.laba.itcompany.domain.exception.PersistenceException;
import solvd.laba.itcompany.persistence.ServiceRepository;
import solvd.laba.itcompany.persistence.config.ConfigurationMyBatis;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ServiceMyBatisRepository implements ServiceRepository {

    @Override
    public void create(Service service) {
        try (SqlSession sqlSession = ConfigurationMyBatis.getSessionFactory().openSession(true)) {
            sqlSession.getMapper(ServiceRepository.class).create(service);
        } catch (Exception e) {
            throw new PersistenceException("Unable to create service", e);
        }
    }

    @Override
    public List<Service> findAll() {
        try (SqlSession sqlSession = ConfigurationMyBatis.getSessionFactory().openSession(true)) {
            return sqlSession.getMapper(ServiceRepository.class).findAll();
        } catch (Exception e) {
            throw new PersistenceException("Unable to find services", e);
        }
    }
}
