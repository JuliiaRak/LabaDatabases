package solvd.laba.itcompany.persistence.impl;

import solvd.laba.itcompany.domain.ProjectFeedback;
import solvd.laba.itcompany.domain.exception.PersistenceException;
import solvd.laba.itcompany.persistence.ProjectFeedbackRepository;
import solvd.laba.itcompany.persistence.ProjectRepository;
import solvd.laba.itcompany.persistence.config.ConnectionPool;
import solvd.laba.itcompany.service.ProjectService;
import solvd.laba.itcompany.service.impl.ProjectServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectFeedbackJdbsRepository implements ProjectFeedbackRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT = "INSERT INTO Project_Feedbacks (project_id, feedback_text) VALUES (?, ?);";
    private static final String FIND_ALL = "SELECT * FROM Project_Feedbacks;";

    @Override
    public void create(ProjectFeedback projectFeedback) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, projectFeedback.getProject().getId());
            preparedStatement.setString(2, projectFeedback.getFeedbackText());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                projectFeedback.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to create project feedback", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<ProjectFeedback> findAll() {
        List<ProjectFeedback> projectFeedbackList = new ArrayList<>();

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long feedbackId = resultSet.getLong("id");
                Long projectId = resultSet.getLong("project_id");
                String feedbackText = resultSet.getString("feedback_text");

                ProjectFeedback projectFeedback = new ProjectFeedback();
                projectFeedback.setId(feedbackId);
                projectFeedback.setFeedbackText(feedbackText);

                ProjectService projectService = new ProjectServiceImpl();
                projectFeedback.setProject(projectService.findById(projectId));

                projectFeedbackList.add(projectFeedback);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find project feedbacks", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return projectFeedbackList;
    }
}
