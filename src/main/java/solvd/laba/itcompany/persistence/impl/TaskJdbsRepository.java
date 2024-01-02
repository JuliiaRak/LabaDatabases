package solvd.laba.itcompany.persistence.impl;

import solvd.laba.itcompany.domain.Employee;
import solvd.laba.itcompany.domain.Project;
import solvd.laba.itcompany.domain.Task;
import solvd.laba.itcompany.domain.exception.PersistenceException;
import solvd.laba.itcompany.persistence.TaskRepository;
import solvd.laba.itcompany.persistence.config.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskJdbsRepository implements TaskRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT = "INSERT INTO Tasks (project_id, employee_id, task_name, task_description, status) VALUES (?, ?, ?, ?, ?);";

    @Override
    public void create(Task task) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, task.getProject().getId());
            preparedStatement.setLong(2, task.getEmployee().getId());
            preparedStatement.setString(3, task.getTaskName());
            preparedStatement.setString(4, task.getTaskDescription());
            preparedStatement.setString(5, task.getStatus());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                task.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to create task", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}

