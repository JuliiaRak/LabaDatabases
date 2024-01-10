package solvd.laba.itcompany.persistence.impl;

import solvd.laba.itcompany.domain.Project;
import solvd.laba.itcompany.domain.Service;
import solvd.laba.itcompany.domain.exception.PersistenceException;
import solvd.laba.itcompany.persistence.ProjectRepository2;
import solvd.laba.itcompany.persistence.config.ConnectionPool;
import solvd.laba.itcompany.service.ClientService;
import solvd.laba.itcompany.service.ProjectService;
import solvd.laba.itcompany.service.impl.ClientServiceImpl;
import solvd.laba.itcompany.service.impl.ProjectServiceImpl;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectJdbsRepository implements ProjectRepository2 {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT = "INSERT INTO Projects (project_name, client_id) VALUES (?, ?);";
    private static final String FIND_BY_ID = "SELECT * FROM Projects WHERE id = ?;";
    private static final String FIND_ALL = "SELECT * FROM Projects;";
    private static final String DELETE = "DELETE FROM Projects WHERE id = ?;";
    private static final String ADD_SERVICE = "INSERT INTO Project_Services (project_id, service_id) VALUES (?, ?);";
    private static final String SELECT_SERVICES_BY_PROJECT_ID = "SELECT Service.* FROM Service " +
            "JOIN Project_Services ON Services.id = Project_Services.service_id " +
            "WHERE Project_Services.project_id = ?";

    @Override
    public void create(Project project) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, project.getProjectName());
            preparedStatement.setLong(2, project.getClient().getId());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                project.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to create project", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Project findById(Long projectId) {
        Project project = new Project();

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, projectId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String projectName = resultSet.getString("project_name");
                Long clientId = resultSet.getLong("client_id");

                project.setId(projectId);
                project.setProjectName(projectName);

                ClientService clientService = new ClientServiceImpl();
                project.setClient(clientService.findById(clientId));

                ProjectService projectService = new ProjectServiceImpl();
                List<Service> services = projectService.findServicesByProjectId(projectId);
                project.setServices(services);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find project by id", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return project;
    }

    @Override
    public List<Project> findAll() {
        List<Project> projects = new ArrayList<>();

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long projectId = resultSet.getLong("id");
                String projectName = resultSet.getString("project_name");
                Long clientId = resultSet.getLong("client_id");

                Project project = new Project();
                project.setId(projectId);
                project.setProjectName(projectName);

                ClientService clientService = new ClientServiceImpl();
                project.setClient(clientService.findById(clientId));

                ProjectService projectService = new ProjectServiceImpl();
                List<Service> services = projectService.findServicesByProjectId(projectId);
                project.setServices(services);

                projects.add(project);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find projects", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return projects;
    }

    @Override
    public void deleteById(Long projectId) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, projectId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Unable to delete project", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void addService(Long projectId, Long serviceId) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(ADD_SERVICE)) {
            preparedStatement.setLong(1, projectId);
            preparedStatement.setLong(2, serviceId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Unable to add service to project", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Service> findServicesByProjectId(Long projectId) {
        List<Service> services = new ArrayList<>();

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SERVICES_BY_PROJECT_ID)) {
            preparedStatement.setLong(1, projectId);
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
            throw new PersistenceException("Unable to find skills of employee", e);
        }

        return services;
    }
}
