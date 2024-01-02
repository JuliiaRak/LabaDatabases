package solvd.laba.itcompany.persistence.impl;

import solvd.laba.itcompany.domain.Employee;
import solvd.laba.itcompany.domain.Vacation;
import solvd.laba.itcompany.domain.exception.PersistenceException;
import solvd.laba.itcompany.persistence.VacationRepository;
import solvd.laba.itcompany.persistence.config.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VacationJdbsRepository implements VacationRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT = "INSERT INTO Vacations (employee_id, start_date, end_date) VALUES (?, ?, ?);";

    @Override
    public void create(Vacation vacation) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, vacation.getEmployee().getId());
            preparedStatement.setDate(2, new java.sql.Date(vacation.getStartDate().getTime()));
            preparedStatement.setDate(3, new java.sql.Date(vacation.getEndDate().getTime()));
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                vacation.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to create vacation", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
