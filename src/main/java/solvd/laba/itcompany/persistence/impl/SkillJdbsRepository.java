package solvd.laba.itcompany.persistence.impl;

import solvd.laba.itcompany.domain.Skill;
import solvd.laba.itcompany.domain.exception.PersistenceException;
import solvd.laba.itcompany.persistence.SkillRepository;
import solvd.laba.itcompany.persistence.config.ConnectionPool;

import java.sql.*;

public class SkillJdbsRepository implements SkillRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT = "INSERT INTO Skills (skill_name) VALUES (?);";

    @Override
    public void create(Skill skill) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, skill.getSkillName());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                skill.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to create skill", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }
}
