package solvd.laba.itcompany.persistence.impl;

import solvd.laba.itcompany.domain.ProjectEmployee;
import solvd.laba.itcompany.domain.exception.PersistenceException;
import solvd.laba.itcompany.persistence.ProjectEmployeeRepository;
import solvd.laba.itcompany.persistence.config.ConnectionPool;

import java.sql.*;

public class ProjectEmployeeJdbsRepository implements ProjectEmployeeRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT = "INSERT INTO Project_Employees (project_id, employee_id, role) VALUES (?, ?, ?);";

    @Override
    public void create(ProjectEmployee projectEmployee) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
            preparedStatement.setLong(1, projectEmployee.getProject().getId());
            preparedStatement.setLong(2, projectEmployee.getEmployee().getId());
            preparedStatement.setString(3, projectEmployee.getRole());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Unable to create project employee", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}

