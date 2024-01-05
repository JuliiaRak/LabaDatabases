package solvd.laba.itcompany.persistence.impl;

import solvd.laba.itcompany.domain.Client;
import solvd.laba.itcompany.domain.exception.PersistenceException;
import solvd.laba.itcompany.persistence.ClientRepository;
import solvd.laba.itcompany.persistence.config.ConfigurationMyBatis;
import org.apache.ibatis.session.SqlSession;

public class ClientMyBatisRepository implements ClientRepository {

    @Override
    public void create(Client client) {
        try (SqlSession sqlSession = ConfigurationMyBatis.getSessionFactory().openSession(true)) {
            sqlSession.getMapper(ClientRepository.class).create(client);
        } catch (Exception e) {
            throw new PersistenceException("Unable to create client", e);
        }
    }

    @Override
    public Client findById(Long clientId) {
        try (SqlSession sqlSession = ConfigurationMyBatis.getSessionFactory().openSession(true)) {
            return sqlSession.getMapper(ClientRepository.class).findById(clientId);
        } catch (Exception e) {
            throw new PersistenceException("Unable to find client by id", e);
        }
    }
}
