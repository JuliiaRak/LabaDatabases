package solvd.laba.itcompany.persistence.impl;

import solvd.laba.itcompany.domain.Department;
import solvd.laba.itcompany.domain.exception.PersistenceException;
import solvd.laba.itcompany.persistence.DepartmentRepository;
import solvd.laba.itcompany.persistence.config.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentJdbsRepository implements DepartmentRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT = "INSERT INTO Departments (department_name) VALUES (?);";
    private static final String UPDATE = "UPDATE Departments SET department_name = ? WHERE id = ?;";
    private static final String FIND_BY_ID = "SELECT * FROM Departments WHERE id = ?;";
    private static final String FIND_ALL = "SELECT * FROM Departments;";
    private static final String DELETE = "DELETE FROM Departments WHERE id = ?;";

    @Override
    public void create(Department department) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, department.getDepartmentName());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                department.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to create department", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void update(Department department) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, department.getDepartmentName());
            preparedStatement.setLong(2, department.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Unable to update department", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Department findById(Long departmentId) {
        Department department = new Department();

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, departmentId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String departmentName = resultSet.getString("department_name");

                department.setDepartmentName(departmentName);
                department.setId(departmentId);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find department by id", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return department;
    }

    @Override
    public List<Department> findAll() {
        List<Department> departments = new ArrayList<>();

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long departmentId = resultSet.getLong("id");
                String departmentName = resultSet.getString("department_name");

                Department department = new Department();
                department.setId(departmentId);
                department.setDepartmentName(departmentName);
                departments.add(department);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find departments", e);
        }

        return departments;
    }

    @Override
    public void deleteById(Long departmentId) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, departmentId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Unable to delete department", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
