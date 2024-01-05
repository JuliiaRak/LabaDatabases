package solvd.laba.itcompany.persistence.impl;

import solvd.laba.itcompany.domain.Department;
import solvd.laba.itcompany.domain.exception.PersistenceException;
import solvd.laba.itcompany.persistence.DepartmentRepository;
import solvd.laba.itcompany.persistence.config.ConfigurationMyBatis;

import java.util.List;
import org.apache.ibatis.session.SqlSession;

public class DepartmentMyBatisRepository implements DepartmentRepository {

    @Override
    public void create(Department department) {
        try (SqlSession sqlSession = ConfigurationMyBatis.getSessionFactory().openSession(true)) {
            sqlSession.getMapper(DepartmentRepository.class).create(department);
        }
        catch (Exception e){
            throw new PersistenceException("Unable to create department", e);
        }
    }

    @Override
    public void update(Department department) {
        try (SqlSession sqlSession = ConfigurationMyBatis.getSessionFactory().openSession(true)) {
            sqlSession.getMapper(DepartmentRepository.class).update(department);
        }
        catch (Exception e){
            throw new PersistenceException("Unable to update department", e);
        }
    }

    @Override
    public Department findById(Long departmentId) {
        try (SqlSession sqlSession = ConfigurationMyBatis.getSessionFactory().openSession(true)) {
            return sqlSession.getMapper(DepartmentRepository.class).findById(departmentId);
        }
        catch (Exception e){
            throw new PersistenceException("Unable to find department by id", e);
        }
    }

    @Override
    public List<Department> findAll() {
        try (SqlSession sqlSession = ConfigurationMyBatis.getSessionFactory().openSession(true)) {
            return sqlSession.getMapper(DepartmentRepository.class).findAll();
        }
        catch (Exception e){
            throw new PersistenceException("Unable to find all departments", e);
        }
    }

    @Override
    public void deleteById(Long departmentId) {
        try (SqlSession sqlSession = ConfigurationMyBatis.getSessionFactory().openSession(true)) {
            sqlSession.getMapper(DepartmentRepository.class).deleteById(departmentId);
        }
        catch (Exception e){
            throw new PersistenceException("Unable to delete department", e);
        }
    }
}
