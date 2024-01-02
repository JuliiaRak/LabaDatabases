package solvd.laba.itcompany.persistence.impl;

import solvd.laba.itcompany.domain.Department;
import solvd.laba.itcompany.domain.Employee;
import solvd.laba.itcompany.domain.exception.PersistenceException;
import solvd.laba.itcompany.persistence.EmployeeRepository;
import solvd.laba.itcompany.persistence.config.ConnectionPool;
import solvd.laba.itcompany.service.DepartmentService;
import solvd.laba.itcompany.service.impl.DepartmentServiceImpl;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeJdbsRepository implements EmployeeRepository {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

    private static final String INSERT =
            "INSERT INTO Employees (department_id, first_name, last_name, position, salary) " +
                    "VALUES (?, ?, ?, ?, ?);";
    private static final String FIND_BY_ID = "SELECT * FROM Employees WHERE id = ?;";
    private static final String FIND_ALL = "SELECT * FROM Employees;";


    @Override
    public void create(Employee employee) {
        Connection connection = CONNECTION_POOL.getConnection();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, employee.getDepartment().getId());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getLastName());
            preparedStatement.setString(4, employee.getPosition());
            preparedStatement.setBigDecimal(5, employee.getSalary());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                employee.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to create employee", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Employee findById(Long employeeId) {
        Employee employee = new Employee();

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, employeeId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                employee.setId(employeeId);

                Long departmentId = resultSet.getLong("department_id");
                DepartmentService departmentService = new DepartmentServiceImpl();
                Department department = departmentService.findById(departmentId);
                employee.setDepartment(department);

                String firstName = resultSet.getString("first_name");
                employee.setFirstName(firstName);

                String lastName = resultSet.getString("last_name");
                employee.setLastName(lastName);

                String position = resultSet.getString("position");
                employee.setPosition(position);

                BigDecimal salary = resultSet.getBigDecimal("salary");
                employee.setSalary(salary);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find employee by id", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }

        return employee;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();

        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Employee employee = new Employee();

                Long employeeId = resultSet.getLong("id");
                employee.setId(employeeId);

                Long departmentId = resultSet.getLong("department_id");
                DepartmentService departmentService = new DepartmentServiceImpl();
                Department department = departmentService.findById(departmentId);
                employee.setDepartment(department);

                String firstName = resultSet.getString("first_name");
                employee.setFirstName(firstName);

                String lastName = resultSet.getString("last_name");
                employee.setLastName(lastName);

                String position = resultSet.getString("position");
                employee.setPosition(position);

                BigDecimal salary = resultSet.getBigDecimal("salary");
                employee.setSalary(salary);

                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Unable to find employees", e);
        }

        return employees;
    }
}
