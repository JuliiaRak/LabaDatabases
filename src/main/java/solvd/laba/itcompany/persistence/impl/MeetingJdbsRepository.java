package solvd.laba.itcompany.persistence.impl;

import solvd.laba.itcompany.domain.Meeting;
import solvd.laba.itcompany.persistence.MeetingRepository;
import solvd.laba.itcompany.domain.exception.PersistenceException;
import solvd.laba.itcompany.persistence.config.ConnectionPool;
import java.sql.*;

public class MeetingJdbsRepository implements MeetingRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT = "INSERT INTO Meetings (project_id, meeting_date, duration) VALUES (?, ?, ?);";

    @Override
    public void create(Meeting meeting) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, meeting.getProject().getId());
            preparedStatement.setDate(2, new java.sql.Date(meeting.getMeetingDate().getTime()));
            preparedStatement.setInt(3, meeting.getDuration());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                meeting.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to create meeting", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
