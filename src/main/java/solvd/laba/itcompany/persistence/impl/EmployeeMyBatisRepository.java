package solvd.laba.itcompany.persistence.impl;

import org.apache.ibatis.session.SqlSession;
import solvd.laba.itcompany.domain.Employee;
import solvd.laba.itcompany.domain.Skill;
import solvd.laba.itcompany.domain.exception.PersistenceException;
import solvd.laba.itcompany.persistence.EmployeeRepository;
import solvd.laba.itcompany.persistence.config.ConfigurationMyBatis;

import java.util.List;

public class EmployeeMyBatisRepository implements EmployeeRepository {

    @Override
    public void create(Employee employee) {
        try (SqlSession sqlSession = ConfigurationMyBatis.getSessionFactory().openSession(true)) {
            sqlSession.getMapper(EmployeeRepository.class).create(employee);
        } catch (Exception e) {
            throw new PersistenceException("Unable to create employee", e);
        }
    }

    @Override
    public Employee findById(Long employeeId) {
        try (SqlSession sqlSession = ConfigurationMyBatis.getSessionFactory().openSession(true)) {
            return sqlSession.getMapper(EmployeeRepository.class).findById(employeeId);
        } catch (Exception e) {
            throw new PersistenceException("Unable to find employee by id", e);
        }
    }

    @Override
    public List<Employee> findAll() {
        try (SqlSession sqlSession = ConfigurationMyBatis.getSessionFactory().openSession(true)) {
            return sqlSession.getMapper(EmployeeRepository.class).findAll();
        } catch (Exception e) {
            throw new PersistenceException("Unable to find all employees", e);
        }
    }

    @Override
    public void addSkill(Long employeeId, Long skilId) {
        try (SqlSession sqlSession = ConfigurationMyBatis.getSessionFactory().openSession(true)) {
            sqlSession.getMapper(EmployeeRepository.class).addSkill(employeeId, skilId);
        } catch (Exception e) {
            throw new PersistenceException("Unable to create employee", e);
        }
    }

    @Override
    public List<Skill> findSkillsByEmployeeId(Long employeeId) {
        try (SqlSession sqlSession = ConfigurationMyBatis.getSessionFactory().openSession(true)) {
            return sqlSession.getMapper(EmployeeRepository.class).findSkillsByEmployeeId(employeeId);
        } catch (Exception e) {
            throw new PersistenceException("Unable to find all employees", e);
        }
    }
}
