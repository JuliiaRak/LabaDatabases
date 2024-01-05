package solvd.laba.itcompany.persistence.impl;

import solvd.laba.itcompany.domain.Meeting;
import solvd.laba.itcompany.domain.exception.PersistenceException;
import solvd.laba.itcompany.persistence.MeetingRepository;
import solvd.laba.itcompany.persistence.config.ConfigurationMyBatis;
import org.apache.ibatis.session.SqlSession;

public class MeetingMyBatisRepository implements MeetingRepository {

    @Override
    public void create(Meeting meeting) {
        try (SqlSession sqlSession = ConfigurationMyBatis.getSessionFactory().openSession(true)) {
            sqlSession.getMapper(MeetingRepository.class).create(meeting);
        } catch (Exception e) {
            throw new PersistenceException("Unable to create meeting", e);
        }
    }
}
