package solvd.laba.itcompany.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    private Long id;
    private String projectName;
    private Client client;
    private List<Employee> employees;
    private List<Service> services;
}
