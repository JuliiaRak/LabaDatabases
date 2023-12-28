package solvd.laba.itcompany.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private Long id;
    private Project project;
    private Employee employee;
    private String taskName;
    private String taskDescription;
    private String status;
}
