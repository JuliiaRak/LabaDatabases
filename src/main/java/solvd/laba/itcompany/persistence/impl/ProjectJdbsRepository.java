package solvd.laba.itcompany.persistence.impl;

import solvd.laba.itcompany.domain.Project;
import solvd.laba.itcompany.persistence.ProjectRepository;
import solvd.laba.itcompany.domain.exception.PersistenceException;import solvd.laba.itcompany.persistence.config.ConnectionPool;
import solvd.laba.itcompany.service.ClientService;
import solvd.laba.itcompany.service.impl.ClientServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectJdbsRepository implements ProjectRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT = "INSERT INTO Projects (project_name, client_id) VALUES (?, ?);";
    private static final String FIND_BY_ID = "SELECT * FROM Projects WHERE id = ?;";
    private static final String FIND_ALL = "SELECT * FROM Projects;";
    private static final String DELETE = "DELETE FROM Projects WHERE id = ?;";

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
}
