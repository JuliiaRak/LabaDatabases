package solvd.laba.itcompany.domain;

import lombok.Data;

@Data
public class Task {
    private Long id;
    private Project project;
    private Employee employee;
    private String taskName;
    private String taskDescription;
    private String status;
}
