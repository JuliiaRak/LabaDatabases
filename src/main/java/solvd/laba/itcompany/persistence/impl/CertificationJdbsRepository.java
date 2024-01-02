package solvd.laba.itcompany.persistence.impl;

import solvd.laba.itcompany.domain.Certification;
import solvd.laba.itcompany.persistence.CertificationRepository;
import solvd.laba.itcompany.persistence.config.ConnectionPool;
import solvd.laba.itcompany.domain.exception.PersistenceException;
import solvd.laba.itcompany.service.EmployeeService;
import solvd.laba.itcompany.service.impl.EmployeeServiceImpl;

import java.sql.Connection;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class CertificationJdbsRepository implements CertificationRepository {

    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT = "INSERT INTO Certifications (employee_id, certification_name, date) VALUES (?, ?, ?);";
    private static final String FIND_ALL = "SELECT * FROM Certifications;";

    @Override
    public void create(Certification certification) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, certification.getEmployee().getId());
            preparedStatement.setString(2, certification.getCertificationName());
            preparedStatement.setDate(3, new java.sql.Date(certification.getDate().getTime()));
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                certification.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to create certification", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Certification> findAll() {
        List<Certification> certifications = new ArrayList<>();

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long certificationId = resultSet.getLong("id");
                Long employeeId = resultSet.getLong("employee_id");
                String certificationName = resultSet.getString("certification_name");
                Date date = resultSet.getDate("date");

                Certification certification = new Certification();
                certification.setId(certificationId);
                EmployeeService employeeService = new EmployeeServiceImpl();
                certification.setEmployee(employeeService.findById(employeeId));
                certification.setCertificationName(certificationName);
                certification.setDate(date);

                certifications.add(certification);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find certifications", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return certifications;
    }

}
