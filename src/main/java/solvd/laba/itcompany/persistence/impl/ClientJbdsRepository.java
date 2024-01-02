package solvd.laba.itcompany.persistence.impl;

import solvd.laba.itcompany.domain.Client;
import solvd.laba.itcompany.persistence.ClientRepository;
import solvd.laba.itcompany.persistence.config.ConnectionPool;
import solvd.laba.itcompany.domain.exception.PersistenceException;

import java.sql.Connection;
import java.sql.*;

public class ClientJbdsRepository implements ClientRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT = "INSERT INTO Clients (client_name) VALUES (?);";
    private static final String FIND_BY_ID = "SELECT * FROM Clients WHERE id = ?;";

    @Override
    public void create(Client client) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, client.getClientName());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                client.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to create client", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Client findById(Long clientId) {
        Client client = new Client();

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, clientId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String clientName = resultSet.getString("client_name");

                client.setClientName(clientName);
                client.setId(clientId);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find client by id", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return client;
    }
}
