package solvd.laba.itcompany.persistence.impl;

import solvd.laba.itcompany.domain.Service;
import solvd.laba.itcompany.domain.exception.PersistenceException;
import solvd.laba.itcompany.persistence.ServiceRepository;
import solvd.laba.itcompany.persistence.config.ConnectionPool;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceJdbsRepository implements ServiceRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT = "INSERT INTO Services (service_name, description, cost) VALUES (?, ?, ?);";
    private static final String FIND_ALL = "SELECT * FROM Services;";

    @Override
    public void create(Service service) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, service.getServiceName());
            preparedStatement.setString(2, service.getDescription());
            preparedStatement.setBigDecimal(3, service.getCost());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                service.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to create service", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Service> findAll() {
        List<Service> services = new ArrayList<>();

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long serviceId = resultSet.getLong("id");
                String serviceName = resultSet.getString("service_name");
                String description = resultSet.getString("description");
                BigDecimal cost = resultSet.getBigDecimal("cost");

                Service service = new Service(serviceId, serviceName, description, cost);

                services.add(service);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find services", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return services;
    }
}
