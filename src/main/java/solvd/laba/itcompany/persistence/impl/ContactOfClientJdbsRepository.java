package solvd.laba.itcompany.persistence.impl;

import solvd.laba.itcompany.domain.ContactOfClient;
import solvd.laba.itcompany.persistence.ContactOfClientRepository;
import solvd.laba.itcompany.persistence.config.ConnectionPool;
import solvd.laba.itcompany.domain.exception.PersistenceException;

import java.sql.Connection;
import java.sql.*;

public class ContactOfClientJdbsRepository implements ContactOfClientRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT = "INSERT INTO Contacts_Of_Clients (client_id, phone_number, address) VALUES (?, ?, ?);";

    @Override
    public void create(ContactOfClient contactOfClient) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, contactOfClient.getClient().getId());
            preparedStatement.setString(2, contactOfClient.getPhoneNumber());
            preparedStatement.setString(3, contactOfClient.getAddress());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                contactOfClient.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to create contact of client", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
