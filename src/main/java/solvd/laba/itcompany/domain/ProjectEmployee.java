package solvd.laba.itcompany.domain;

import lombok.Data;

@Data
public class ProjectEmployee {
    private Project project;
    private Employee employee;
    private String role;
}
