package solvd.laba.itcompany.domain;

import lombok.Data;
import java.util.List;

@Data
public class Project {
    private Long id;
    private String projectName;
    private Client client;
    private List<Employee> employees;
    private List<Service> services;
}
